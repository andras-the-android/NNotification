package com.example.andrasnemeth.nnotification

import androidx.core.app.NotificationCompat

/**
 * Created by guni8 on 2016. 04. 03..
 */
class BundledNotifictions(private val notificationHelper: BaseNotificationHelper) {
    private var notificationCounter = 0
    fun addNotificationToBundle() {
        val notificationBuilder = notificationHelper.createNotificationBuilder()
        if (notificationCounter == 1) {
            addGroupSummaryNotification()
        }
        notificationBuilder!!.setGroup(KEY_NOTIFICATION_GROUP)
        notificationHelper.showNotification(notificationBuilder, NOTIFICATION_ID_GROUP_SUMMARY + notificationCounter)
        ++notificationCounter
    }

    fun addGroupSummaryNotification() {
        val notificationBuilder = NotificationCompat.Builder(notificationHelper.context)
                .setSmallIcon(R.drawable.ic_launcher)
                .setStyle(NotificationCompat.BigTextStyle()
                        .setSummaryText("This is the title of the group"))
                .setGroupSummary(true)
                .setGroup(KEY_NOTIFICATION_GROUP)
        notificationHelper.showNotification(notificationBuilder, NOTIFICATION_ID_GROUP_SUMMARY)
    }

    companion object {
        private const val KEY_NOTIFICATION_GROUP = "KEY_NOTIFICATION_GROUP"
        private const val NOTIFICATION_ID_GROUP_SUMMARY = 666
    }

}