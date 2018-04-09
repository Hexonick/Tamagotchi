package saucebrune.tamagotchi;

import android.os.AsyncTask;
import android.os.Handler;
import android.widget.TextView;

public class BackgroundTask extends AsyncTask<TextView,Integer,Void>{
    private boolean sorti = false;
    private TextView txtExpMonstre1 = null;
    private int[] expMonstre = new int[4];
    private int[] speed = new int[4];
    private int[] gainExp = new int[4];

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //A enlever apres initialisation avec la BD
        for(int i = 0; i<4;i++){
            expMonstre[i] = -1;
            speed[i] = 3000;
            gainExp[i] = 1;
        }
        //-----------------------------------------
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
        txtExpMonstre1.setText(String.valueOf(expMonstre[0]));
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        sorti = false;
        //Ajouter les valeurs dans la BD
    }
}
