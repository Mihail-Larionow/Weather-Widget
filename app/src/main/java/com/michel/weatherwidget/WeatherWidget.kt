package com.michel.weatherwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.res.Configuration
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [WeatherWidgetConfigureActivity]
 */
class WeatherWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them

        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // When the user deletes the widget, delete the preference associated with it.
        for (appWidgetId in appWidgetIds) {
            deleteTitlePref(context, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
) {

    val weatherWidgetView = WeatherWidgetView(context)
    weatherWidgetView.setSize(
        getWidgetWidth(appWidgetId, context),
        getWidgetHeight(appWidgetId, context),
    )

    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.weather_widget)
    views.setImageViewBitmap(R.id.weatherView, weatherWidgetView.draw(cornerRadius = 10f))

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

internal fun getWidgetWidth(widgetId: Int, context: Context): Int {
    val appWidgetManager = AppWidgetManager.getInstance(context)
    return if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        appWidgetManager.getAppWidgetOptions(widgetId)
            .getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH, 0)
    } else {
        appWidgetManager.getAppWidgetOptions(widgetId)
            .getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH, 0)
    }
}

internal fun getWidgetHeight(widgetId: Int, context: Context): Int {
    val appWidgetManager = AppWidgetManager.getInstance(context)
    return if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        appWidgetManager.getAppWidgetOptions(widgetId)
            .getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT, 0)
    } else {
        appWidgetManager.getAppWidgetOptions(widgetId)
            .getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT, 0)
    }
}