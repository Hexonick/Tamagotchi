package saucebrune.tamagotchi;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.w3c.dom.Text;

import static saucebrune.tamagotchi.MainActivity.expMonstre;
import static saucebrune.tamagotchi.MainActivity.gainExp;
import static saucebrune.tamagotchi.MainActivity.speed;

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
                    expMonstre[0]+= gainExp[0];
                    try {
                        Thread.sleep(speed[0]);
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
    public IBinder onBind(Intent intent) {
        return monBinder;
    }

    @Override
    public boolean onUnbind(Intent intent){
        return false;
    }

    public class MonBinderDActivite extends Binder{
        ServiceActivity getMonService(){
            return ServiceActivity.this;
        }
    }

    public void stopRun(){
        sorti = false;
    }
}
