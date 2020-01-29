package com.example.andrasnemeth.nnotification

import android.widget.RemoteViews
import androidx.core.app.NotificationCompat

/**
 * Created by guni8 on 2016. 04. 04..
 */
class CustomViewNotifications(private val notificationHelper: BaseNotificationHelper) {
    fun show() {
        val remoteViews = RemoteViews(notificationHelper.context.packageName, R.layout.notification_custom)
        remoteViews.setTextViewText(R.id.content_title, "This is the title of the custom view notification")
        val remoteViewsBig = RemoteViews(notificationHelper.context.packageName, R.layout.notification_big_custom)
        remoteViewsBig.setTextViewText(R.id.content_title, "This is the title of the big custom view notification")
        remoteViewsBig.setTextViewText(R.id.content_text, "This is the text of the big custom view notification")
        val builder = notificationHelper.createNotificationBuilder()
                .setSmallIcon(R.drawable.ic_launcher)
                .setAutoCancel(true)
                .setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViewsBig)
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
        notificationHelper.showNotification(builder, NOTIFICATION_ID_CUSTOM)
    }

    companion object {
        private const val NOTIFICATION_ID_CUSTOM = 4
    }

}