package saucebrune.tamagotchi;

import android.os.AsyncTask;
import android.widget.TextView;

import static saucebrune.tamagotchi.MainActivity.accountData;
import static saucebrune.tamagotchi.MainActivity.myDB;

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
        int[] exp = myDB.selectExpMonstre(myDB.selectId(accountData.getPseudo()));
        accountData.setExpMonsre(exp[0],0);
        //Creer un switch avec le textview pour aller chercher le id
        while(sorti){
            accountData.setExpMonsre(accountData.getExpMonstre(0) + accountData.getGainExp(0),0);
            publishProgress(accountData.getExpMonstre(0));
            try {
                Thread.sleep(accountData.getSpeed(0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        txtExpMonstre1.setText(String.valueOf(accountData.getExpMonstre(0)));
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        sorti = false;
    }
}
