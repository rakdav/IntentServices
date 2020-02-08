package com.example.intentservices;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.SystemClock;


public class SimpleIntentService extends IntentService {
    public static final String ACTION_1="MyAction";
    public SimpleIntentService() {
        super("SimpleIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent broadcastIntent=new Intent();
        broadcastIntent.setAction(SimpleIntentService.ACTION_1);
        for(int i=0;i<=100;i++)
        {
            broadcastIntent.putExtra("percel",i);
            sendBroadcast(broadcastIntent);
            SystemClock.sleep(100);
        }
    }
}
