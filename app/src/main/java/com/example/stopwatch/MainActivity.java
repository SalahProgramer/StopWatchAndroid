package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkInstance(savedInstanceState);

        runTime();
    }

    private void checkInstance(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("SECONDS");
            running = savedInstanceState.getBoolean("RUNNING");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SECONDS", seconds);
        outState.putBoolean("RUNNING", running);


    }

    private void runTime() {
        final TextView txtTime = findViewById(R.id.textView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = hours + " : " + minutes + " : " + secs;
                txtTime.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);

            }
        });

    }

    public void btnStartOnClick(View view) {
        running = true;
    }

    public void btnStopOnClick(View view) {
        running = false;
    }

    public void btnResetOnClick(View view) {
        running = false;
        seconds = 0;
    }


}