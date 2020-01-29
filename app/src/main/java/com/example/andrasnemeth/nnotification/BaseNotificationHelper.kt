package com.example.andrasnemeth.nnotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

/**
 * Created by guni8 on 2016. 04. 03..
 */

private const val CHANNEL_ID = "myChannel"

class BaseNotificationHelper(val context: Context) {

    val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.cannond)

    fun createNotificationBuilder(): NotificationCompat.Builder {
        val mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Content title")
                .setContentText("Content text")
                .setLargeIcon(bitmap)
                .setAutoCancel(true)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
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

    fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}