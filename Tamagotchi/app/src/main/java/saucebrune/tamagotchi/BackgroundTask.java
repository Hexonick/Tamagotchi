package saucebrune.tamagotchi;

import android.os.AsyncTask;
import android.widget.TextView;

import static saucebrune.tamagotchi.MainActivity.expMonstre;
import static saucebrune.tamagotchi.MainActivity.gainExp;
import static saucebrune.tamagotchi.MainActivity.speed;

public class BackgroundTask extends AsyncTask<TextView,Integer,Void>{
    private boolean sorti = false;
    private TextView txtExpMonstre1 = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        sorti = true;
    }

    @Override
    protected Void doInBackground(TextView... params) {
        txtExpMonstre1 = params[0];
        while(sorti){
            expMonstre[0] += gainExp[0]; //Gain d'expérience
            publishProgress(expMonstre[0]);
            try {
                Thread.sleep(speed[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        txtExpMonstre1.setText(values[0]);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        sorti = false;
        //Ajouter les valeurs dans la BD
    }
}
