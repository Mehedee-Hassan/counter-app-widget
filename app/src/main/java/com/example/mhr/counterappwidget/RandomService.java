package com.example.mhr.counterappwidget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by mhr on 10/20/16.
 */
public class RandomService extends Service {

    public static final String ACTION_RANDOM_NUMBER =
            "com.examples.appwidget.ACTION_RANDOM_NUMBER";


    private static int sRandomNumber ;
    public static int getsRandomNumber(){
        return sRandomNumber;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Log.d("test == ","service started");


        sRandomNumber = (int) (Math.random() * 100);

        RemoteViews views = new RemoteViews(getPackageName(),
                R.layout.counter_widget_layout);



        views.setTextViewText(R.id.text_visible,"visible----------");
        views.setTextViewText(R.id.text_number ,String.valueOf(sRandomNumber));


        PendingIntent refreshIntent = PendingIntent.getService(this,
                0,new Intent(this,RandomService.class),0);
        views.setOnClickPendingIntent(R.id.button_refresh ,refreshIntent);



        PendingIntent appIntent = PendingIntent.getActivity(this ,0,new Intent(this,MainActivity.class),0);
        views.setOnClickPendingIntent(R.id.container,appIntent);



        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        ComponentName widget = new ComponentName(this,CounterAppWidget.class);
        manager.updateAppWidget(widget,views);




        Intent broadcast = new Intent(ACTION_RANDOM_NUMBER);
        sendBroadcast(broadcast);

        stopSelf();
        return START_NOT_STICKY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
