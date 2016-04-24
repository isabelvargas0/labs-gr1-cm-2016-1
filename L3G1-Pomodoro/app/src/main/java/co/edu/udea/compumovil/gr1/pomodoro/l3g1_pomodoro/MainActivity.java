package co.edu.udea.compumovil.gr1.pomodoro.l3g1_pomodoro;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTimer;
    TextView timeViewer;

    private BroadcastReceiver broadcastReceiver;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateDataToUI(intent);
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver((broadcastReceiver),
                new IntentFilter(CountDownService.COUNTDOWN_BR));
        editTimer = (EditText) findViewById(R.id.edtTimerValue);
        timeViewer = (TextView) findViewById(R.id.tvTimeCount);

    }

    private void updateDataToUI(Intent intent) {
        timeViewer.setText(String.valueOf(intent.getLongExtra(CountDownService.COUNTDOWN, 1L)));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");
        stopService(intent);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    public void startTimer(View v) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 100); // see this max value coming back here, we animale towards that value
        animation.setDuration(5000); //in milliseconds
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
        intent = new Intent(this, CountDownService.class);
        intent.putExtra(CountDownService.COUNTDOWN_TIME, Long.valueOf(editTimer.getText().toString()));
        startService(intent);
    }
}
