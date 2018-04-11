package saucebrune.tamagotchi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    public static String[] nomMonstre = new String[4];
    public static int nivAccount;
    public static int[] tempsVivant = new int[4];
    public static int[] nivMonstre = new int[4];
    public static int[] expMonstre = new int[4];
    public static int[] speed = new int[4];
    public static int[] gainExp = new int[4];
    public static int tempsOnDestroy;

    public static ServiceActivity srvActivity;
    public static BackgroundTask asyncActivity;
    public static boolean boolSrvActivity;
    public static boolean boolAsyncActivity;

    private static final int REQUEST_CODE = 1;
    private ServiceConnection monServiceConnection;
    private ServiceActivity monService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent bindIntent = new Intent(this, ServiceActivity.class);
        setServiceConnection();
        //Appel du onCreate de ServiceActivity
        bindService(bindIntent,monServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public void onClickLogin(View button){
        Intent login = new Intent(this,LoginActivity.class);
        init();
        startActivityForResult(login, REQUEST_CODE);
    }

    public void onClickJouer(View button){
        Intent intent = new Intent(this,MonsterList.class);
        startActivity(intent);
    }

    public void onClickQuitter(View button){ finish(); }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String info = data.getStringExtra("etxtPseudo");
                TextView pseudo = (TextView) findViewById(R.id.txtPseudo);
                Button btnJouer = (Button)findViewById(R.id.btnJouer);
                btnJouer.setEnabled(true);
                pseudo.setAlpha(1);
                pseudo.setText(info);
            }
        }
    }

    private void init(){
        for(int i = 0; i<4;i++){
            gainExp[i] = 1;
            tempsVivant[i] = 0;
            nomMonstre[i] = "";
            expMonstre[i] = -1;
            nivMonstre[i] = 0;
            speed[i] = 3000;
        }
        nivAccount = 0;
        tempsOnDestroy = 0;
        boolAsyncActivity = true;
        boolSrvActivity = false;
        asyncActivity = new BackgroundTask();
        srvActivity = new ServiceActivity();
    }

    //Fonction relier au service
    //--------------------------------------------------------

    private void setServiceConnection() {

        monServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceDisconnected(ComponentName name) {
                monService = null;
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                monService = ((ServiceActivity.MonBinderDActivite) service).getMonService();
            }
        };
    }

    public void demarrerService() {
        Intent serviceIntent = new Intent(this, ServiceActivity.class);
        startService(serviceIntent);
    }

    public void arreterService() {
        Intent serviceIntent = new Intent(this, ServiceActivity.class);
        stopService(serviceIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String givenDateString = String.valueOf(Calendar.getInstance().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        try {
            Date mDate = sdf.parse(givenDateString);
            long timeInMilliseconds = mDate.getTime();
            tempsOnDestroy = (int)(long)(timeInMilliseconds / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolSrvActivity = true;
        demarrerService();
    }
}