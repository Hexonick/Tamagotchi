package saucebrune.tamagotchi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class gameplayActivity extends Activity{
    private boolean firstTime = true;
    public BackgroundTask backTask = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameplay_activity);
        //Va chercher l'experience des monstres dans la BD ici
        //int[] expMonstre = {-1,-1,-1,-1};
        TextView view = (TextView)findViewById(R.id.txtExp);
        if(firstTime){ backTask = new BackgroundTask();
        firstTime = false;}
        backTask.execute(view);
    }

    public void receiveData(int exp){

    }

    public void onClickRetour(View view){ finish();}
}
