package com.example.andrasnemeth.nnotification

import android.os.Handler
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat

/**
 * Created by guni8 on 2016. 04. 04..
 */
class CustomViewNotifications(private val notificationHelper: BaseNotificationHelper) {

    private var remoteViewsBig: RemoteViews? = null
    private var counter = 0
    private var builder: NotificationCompat.Builder? = null

    fun show() {
        val remoteViews = RemoteViews(notificationHelper.context.packageName, R.layout.notification_custom)
        remoteViews.setTextViewText(R.id.content_title, "This is the title of the custom view notification")

        remoteViewsBig = RemoteViews(notificationHelper.context.packageName, R.layout.notification_big_custom)
        remoteViewsBig!!.setTextViewText(R.id.content_title, "This is the title of the big custom view notification")
        remoteViewsBig!!.setTextViewText(R.id.content_text, "#${counter++} This is the text of the big custom view notification")
        builder = notificationHelper.createNotificationBuilder()
                .setSmallIcon(R.drawable.ic_launcher)
                .setAutoCancel(true)
                .setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViewsBig)
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .setOnlyAlertOnce(true)
        notificationHelper.showNotification(builder, NOTIFICATION_ID_CUSTOM)

        counter = 0
        update()
    }

    private fun update() {
        Handler().postDelayed({
            remoteViewsBig!!.setTextViewText(R.id.content_text, "#${counter++} This is the text of the big custom view notification")
            notificationHelper.showNotification(builder, NOTIFICATION_ID_CUSTOM)
            if (counter <= 30) update()
        }, 1000)
    }

    companion object {
        private const val NOTIFICATION_ID_CUSTOM = 4
    }

}