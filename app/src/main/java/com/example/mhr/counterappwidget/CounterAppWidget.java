package com.example.mhr.counterappwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

/**
 * Created by mhr on 10/20/16.
 */
public class CounterAppWidget extends AppWidgetProvider {





    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        context.startService(new Intent(context,RandomService.class));

    }
}
