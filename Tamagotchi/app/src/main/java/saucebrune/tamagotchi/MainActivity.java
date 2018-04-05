package saucebrune.tamagotchi;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

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

    public void demarrerService(View view) {
        Intent serviceIntent = new Intent(this, ServiceActivity.class);
        startService(serviceIntent);
    }

    public void arreterService(View view) {
        Intent serviceIntent = new Intent(this, ServiceActivity.class);
        stopService(serviceIntent);
    }

}