package com.example.intentservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private Button stop;
    private TextView percelText;
    private ProgressBar progressBar;
    private Intent serviceIntent;
    private ResponseReciver reciver=new ResponseReciver();
    private class ResponseReciver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(SimpleIntentService.ACTION_1))
            {
                int value=intent.getIntExtra("percel",-1);
                new ShowProgressBarTask().execute(value);
            }
        }
    }

    class ShowProgressBarTask extends AsyncTask<Integer,Integer,Integer>
    {

        @Override
        protected Integer doInBackground(Integer... integers) {
            return integers[0];
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            progressBar.setProgress(integer);
            percelText.setText(integer+" % Loaded");
            if(integer==100)
            {
                percelText.setText("Completed");
                start.setEnabled(true);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.start);
        stop=findViewById(R.id.stop);
        percelText=findViewById(R.id.text_percel);
        progressBar=findViewById(R.id.progressBar);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setEnabled(false);
                serviceIntent=new Intent(getApplicationContext(),SimpleIntentService.class);
                stopService(serviceIntent);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(serviceIntent!=null)
                {

                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(reciver,new IntentFilter(SimpleIntentService.ACTION_1));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(reciver);
    }
}
