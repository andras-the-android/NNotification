package com.example.andrasnemeth.nnotification

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

/**
 * Created by guni8 on 2016. 04. 03..
 */
class BaseNotificationHelper(val context: Context) {
    val bitmap: Bitmap
    fun createNotificationBuilder(): NotificationCompat.Builder {
        val mBuilder = NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Content title")
                .setContentText("Content text")
                .setLargeIcon(bitmap)
                .setAutoCancel(true)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
        val resultPendingIntent = pendingIntent
        mBuilder.setContentIntent(resultPendingIntent)
        mBuilder.addAction(R.drawable.ic_launcher, "First action", resultPendingIntent)
        mBuilder.addAction(R.drawable.ic_launcher, "Second action", resultPendingIntent)
        return mBuilder
    }

    val pendingIntent: PendingIntent
        get() {
            val resultIntent = Intent(context, MainActivity::class.java)
            return PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT)
        }

    fun showNotification(builder: NotificationCompat.Builder?, id: Int) {
        showNotification(builder!!.build(), id)
    }

    fun showNotification(builder: Notification.Builder, id: Int) {
        showNotification(builder.build(), id)
    }

    private fun showNotification(notification: Notification, id: Int) {
        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(id, notification)
    }

    init {
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.cannond)
    }
}