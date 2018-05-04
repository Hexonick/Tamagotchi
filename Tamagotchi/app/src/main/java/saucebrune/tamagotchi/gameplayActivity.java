package saucebrune.tamagotchi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static saucebrune.tamagotchi.MainActivity.accountData;
import static saucebrune.tamagotchi.MainActivity.boolSrvActivity;
import static saucebrune.tamagotchi.MainActivity.myDB;

public class gameplayActivity extends Activity{
    private boolean firstTime = true;
    public BackgroundTask backTask = null;
    MainActivity main = new MainActivity();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameplay_activity);
        if(boolSrvActivity){
            main.arreterService();
            boolSrvActivity = false;
        }
        int[] values = myDB.selectExpMonstre(myDB.selectId(accountData.getPseudo()));
        accountData.setExpMonsre(values[0],0);
        //accountData.setExpMonsre(values[myDB.selectId(accountData.getPseudo())-1],myDB.selectId(accountData.getPseudo())-1);
        TextView view = (TextView)findViewById(R.id.txtExp);
        if(firstTime){ backTask = new BackgroundTask();
        firstTime = false;}
        backTask.execute(view);
    }

    public void onClickRetour(View view){
        myDB.updateExpMonstre(0,accountData.getNomMonstre(0));
        backTask.onCancelled();
        main.demarrerService();
        boolSrvActivity = true;
        finish();
    }
}
