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

public class MainActivity extends AppCompatActivity {

    public static ServiceActivity srvActivity;
    public static BackgroundTask asyncActivity;
    public static boolean boolSrvActivity;
    public static boolean boolAsyncActivity;
    public static DatabaseHelper myDB;

    private static final int REQUEST_CODE = 1;
    private ServiceConnection monServiceConnection;
    private ServiceActivity monService;
    static AccountData accountData;

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
            accountData = AccountData.getInstance();
            accountData.setPseudo(data.getStringExtra("etxtPseudo"));
            accountData.setPassword(data.getStringExtra("etxtPassword"));
            TextView pseudo = (TextView) findViewById(R.id.txtPseudo);
            Button btnJouer = (Button)findViewById(R.id.btnJouer);
            btnJouer.setEnabled(true);
            pseudo.setAlpha(1);
            pseudo.setText(accountData.getPseudo());
            if (resultCode == RESULT_OK) {
                myDB.insertIntoTblAccount(accountData.getPseudo(),accountData.getPassword(),0,accountData.getNivAccount(),accountData.getSpeed(0),accountData.getGainExp(0));
            }
        }
    }

    private void init(){
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
}