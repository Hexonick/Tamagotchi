package saucebrune.tamagotchi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static saucebrune.tamagotchi.MainActivity.asyncActivity;

public class gameplayActivity extends Activity{
    private boolean firstTime = true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameplay_activity);
        //Va chercher l'experience des monstres dans la BD ici
        TextView view = (TextView)findViewById(R.id.txtExp);
        asyncActivity.execute(view);
    }

    public void onClickRetour(View view){ finish();}
}
