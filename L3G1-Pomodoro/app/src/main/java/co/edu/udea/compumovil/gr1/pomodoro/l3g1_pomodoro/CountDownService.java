package co.edu.udea.compumovil.gr1.pomodoro.l3g1_pomodoro;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class CountDownService extends Service {

    private LocalBroadcastManager broadcastManager;
    public static final String COUNTDOWN_TIME = "time";
    public static final String COUNTDOWN = "countdown";
    private long millis;

    public CountDownService() {
    }

    private final static String TAG = "BroadcastService";

    public static final String COUNTDOWN_BR = "co.edu.udea.compumovil.pomodoro.countdown_br";
    Intent bi = new Intent(COUNTDOWN_BR);

    CountDownTimer cdt = null;

    @Override
    public void onCreate() {
        super.onCreate();
        broadcastManager = LocalBroadcastManager.getInstance(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cdt.cancel();
        //handler.removeCallbacks(sendUpdatesToUI);
        Log.i(TAG, "Timer cancelled");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        millis = intent.getLongExtra(COUNTDOWN_TIME, 1L) * 1000;
        cdt = new CountDownTimer(millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished / 1000);
                bi.putExtra(COUNTDOWN, millisUntilFinished / 1000);
                broadcastManager.sendBroadcast(bi);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Timer finished");
                Intent intent1 = new Intent(getApplicationContext(), AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                        123312132, intent1, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent);
            }
        };
        cdt.start();
        //handler.removeCallbacks(sendUpdatesToUI);
        //handler.postDelayed(sendUpdatesToUI, 1000);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}
