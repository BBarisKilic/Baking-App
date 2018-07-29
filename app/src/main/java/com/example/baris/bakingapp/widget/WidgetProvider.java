package com.example.baris.bakingapp.widget;

import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.baris.bakingapp.R;
import com.example.baris.bakingapp.helper.RecipeData;
import com.example.baris.bakingapp.model.RP;
import com.example.baris.bakingapp.MainActivity;

public class WidgetProvider extends AppWidgetProvider
{
    static void updateWidget(Context context, AppWidgetManager widgetManager, int widgetId)
    {
        RP rp = RecipeData.recipe;
        String recipeNameText = "";
        if (rp != null) {
            recipeNameText = rp.getRecipeName();
        }
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        remoteViews.setTextViewText(R.id.widget_tv, recipeNameText);
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.widget_tv, pendingIntent);
        Intent intentRefresh = new Intent(context, WidgetService.class);
        intentRefresh.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, widgetId);
        remoteViews.setRemoteAdapter(R.id.widget_lv, intentRefresh);
        widgetManager.updateAppWidget(widgetId, remoteViews);
        widgetManager.notifyAppWidgetViewDataChanged(widgetId, R.id.widget_lv);
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager widgetManager, int[] id) {
        for (int appWidgetId : id) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            updateWidget(context, widgetManager, appWidgetId);
        }
    }

    @Override
    public void onDisabled(Context context) {
    }
}

