package saucebrune.tamagotchi;

import android.os.AsyncTask;
import android.os.Handler;
import android.widget.TextView;

public class BackgroundTask extends AsyncTask<TextView,Void,Void>{
    private boolean sorti = false;
    private long timer = 5000;
    private int exp = -1;
    private TextView txtExpMonstre1 = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        sorti = true;
    }

    @Override
    protected Void doInBackground(TextView... params) {
        txtExpMonstre1 = params[0];
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(sorti){
                    exp++; //Gain d'expérience
                    txtExpMonstre1.setText(String.valueOf(exp));
                    handler.postDelayed(this,5000);
                }
            }
        };
        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        sorti = false;
    }
}
