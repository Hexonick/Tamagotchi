package saucebrune.tamagotchi;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import static saucebrune.tamagotchi.MainActivity.accountData;
import static saucebrune.tamagotchi.MainActivity.myDB;

public class ServiceActivity extends Service {

    private IBinder monBinder;
    private boolean sorti = true;

    @Override
    public void onCreate(){
        super.onCreate();
        monBinder = new MonBinderDActivite();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(sorti){
                    accountData.setExpMonsre(accountData.getExpMonstre(0) + accountData.getGainExp(0),0);
                    try {
                        Thread.sleep(accountData.getSpeed(0));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return monBinder; }

    @Override
    public boolean onUnbind(Intent intent){ return false; }

    public class MonBinderDActivite extends Binder{
        ServiceActivity getMonService(){
            return ServiceActivity.this;
        }
    }

    public void stopRun(){
        sorti = false;
        myDB.updateExpMonstre(accountData.getExpMonstre(0),accountData.getNomMonstre(0));
    }
}
