package saucebrune.tamagotchi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static saucebrune.tamagotchi.MainActivity.accountData;
import static saucebrune.tamagotchi.MainActivity.boolSrvActivity;
import static saucebrune.tamagotchi.MainActivity.monService;
import static saucebrune.tamagotchi.MainActivity.myDB;

public class gameplayActivity extends Activity{
    private boolean firstTime = true;
    public BackgroundTask backTask = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameplay_activity);
        if(boolSrvActivity){
            monService.stopRun();
            boolSrvActivity = false;
        }
        int[] values = myDB.selectExpMonstre(myDB.selectId(accountData.getPseudo()));
        int val = values[0];
        accountData.setExpMonsre(values[0],0);
        TextView monstre = (TextView)findViewById(R.id.txtMonstre1);
        monstre.setText(myDB.selectNomMonstre(myDB.selectId(accountData.getPseudo())));
        TextView view = (TextView)findViewById(R.id.txtExp);
        if(firstTime){ backTask = new BackgroundTask();
        firstTime = false;}
        backTask.execute(view);
    }

    public void onClickRetour(View view){
        myDB.updateExpMonstre(accountData.getExpMonstre(0),myDB.selectId(accountData.getPseudo()));
        backTask.onCancelled();
        Intent serviceIntent = new Intent(this, ServiceActivity.class);
        startService(serviceIntent);
        boolSrvActivity = true;
        finish();
    }
}
