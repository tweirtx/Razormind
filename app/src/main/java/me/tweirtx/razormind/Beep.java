package me.tweirtx.razormind;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class Beep extends AppWidgetProvider {

	public static String WIDGET_BUTTON = "WIDGET_BUTTON_CLICK";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		RemoteViews remoteViews;
		ComponentName buttonWidget;

		// Get our add widget view.
		remoteViews = new RemoteViews(context.getPackageName(), R.layout.beep);
		buttonWidget = new ComponentName(context, Beep.class);

		// Set our intent for the button.
		Intent intent = new Intent(context, this.getClass());
		intent.setAction(Beep.WIDGET_BUTTON);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// Set our app widget up with our intent.
		remoteViews.setOnClickPendingIntent(R.id.widgetAdd, pendingIntent);
		appWidgetManager.updateAppWidget(buttonWidget, remoteViews);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);

		// Check if it was our button that was clicked.
		if (Beep.WIDGET_BUTTON.equals(intent.getAction())) {
			Toast.makeText(context, "Hi Travis!\nLook! I work-y!", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onEnabled(Context context) {
		// Enter relevant functionality for when the first widget is created
	}

	@Override
	public void onDisabled(Context context) {
		// Enter relevant functionality for when the last widget is disabled
	}

}

