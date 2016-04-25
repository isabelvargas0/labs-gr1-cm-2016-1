package co.edu.udea.compumovil.gr1.pomodoro.l3g1_pomodoro;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class CountDownService extends Service {

    public static final String COUNTDOWN_TIME = "time";
    public static final String MINUTES = "minutes";
    public static final String SECONDS = "seconds";
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
        millis = intent.getLongExtra(COUNTDOWN_TIME, 1L) * 60000;
        cdt = new CountDownTimer(millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
                Log.i(TAG, "Countdown minutes remaining: " + minutes);
                bi.putExtra(MINUTES, minutes);
                bi.putExtra(SECONDS, seconds);
                sendBroadcast(bi);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Timer finished");
                Intent intent1 = new Intent(getApplicationContext(), AlarmActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                        12345, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
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
