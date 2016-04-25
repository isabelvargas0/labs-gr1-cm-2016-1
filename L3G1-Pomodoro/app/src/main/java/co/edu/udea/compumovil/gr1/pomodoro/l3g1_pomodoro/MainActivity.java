package co.edu.udea.compumovil.gr1.pomodoro.l3g1_pomodoro;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTimer;
    TextView timeViewer;
    Button startButton;
    Button stopButton;

    private BroadcastReceiver broadcastReceiver;
    private Intent intent;
    private boolean timerStarted = false;
    ProgressBar progressBar;
    ObjectAnimator animation;
    int seconds;
    int minutes;

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
        registerReceiver((broadcastReceiver),
                new IntentFilter(CountDownService.COUNTDOWN_BR));

        startButton = (Button) findViewById(R.id.btnStartTime);
        stopButton = (Button) findViewById(R.id.btnStopTime);
        editTimer = (EditText) findViewById(R.id.edtTimerValue);
        timeViewer = (TextView) findViewById(R.id.tvMinuteCount);
        intent = new Intent(this, CountDownService.class);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        // see this max value coming back here, we animate towards that value
        //animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);

    }

    private void updateDataToUI(Intent intent) {
        seconds = intent.getIntExtra(CountDownService.SECONDS, 1);
        minutes = intent.getIntExtra(CountDownService.MINUTES, 1);
        timeViewer.setText(String.format("%02d", seconds / 60)
                + ":" + String.format("%02d", seconds % 60));
        progressBar.setProgress(seconds);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver((broadcastReceiver),
                new IntentFilter(CountDownService.COUNTDOWN_BR));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
        unregisterReceiver(broadcastReceiver);
    }

    public void startTimer(View v) {
        progressBar.setMax(Integer.valueOf(editTimer.getText().toString()) * 60);
        // animation.setDuration(Long.valueOf(editTimer.getText().toString()) * 60000); //in milliseconds
        //animation.setDuration(20000); //in milliseconds
        //animation.setInterpolator(new DecelerateInterpolator());
        //animation.start();
        intent.putExtra(CountDownService.COUNTDOWN_TIME, Long.valueOf(editTimer.getText().toString()));
        timerStarted = true;
        startButton.setVisibility(View.GONE);
        stopButton.setVisibility(View.VISIBLE);
        startService(intent);
    }

    public void pauseTimer(View v) {
        startButton.setVisibility(View.VISIBLE);
        stopButton.setVisibility(View.GONE);
        //animation.cancel();
        timerStarted = false;
    }
}
