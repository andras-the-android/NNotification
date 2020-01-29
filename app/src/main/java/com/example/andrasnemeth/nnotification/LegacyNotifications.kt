package com.example.andrasnemeth.nnotification

import androidx.core.app.NotificationCompat

/**
 * Created by guni8 on 2016. 04. 03..
 */
class LegacyNotifications(private val notificationHelper: BaseNotificationHelper) {
    fun showPlainNotification() {
        notificationHelper.showNotification(notificationHelper.createNotificationBuilder(), NOTIFICATION_ID_PLAIN)
    }

    fun showInboxSytleNotification() {
        val notificationBuilder = notificationHelper.createNotificationBuilder()
        notificationBuilder!!.setStyle(createInboxStyle())
        notificationHelper.showNotification(notificationBuilder, NOTIFICATION_ID_INBOX)
    }

    fun showBigPictureStyleNotification() {
        val notificationBuilder = notificationHelper.createNotificationBuilder()
        notificationBuilder!!.setStyle(createBigPictureStyle())
        notificationHelper.showNotification(notificationBuilder, NOTIFICATION_ID_BIG_PICTURE)
    }

    fun showBigTextStyleNotification() {
        val notificationBuilder = notificationHelper.createNotificationBuilder()
        notificationBuilder!!.setStyle(createBigTextStyle())
        notificationHelper.showNotification(notificationBuilder, NOTIFICATION_ID_BIG_TEXT)
    }

    private fun createBigTextStyle(): NotificationCompat.Style {
        val bigTextStyle = NotificationCompat.BigTextStyle()
        bigTextStyle.bigText("Big text")
        bigTextStyle.setBigContentTitle("Big content tite")
        bigTextStyle.setSummaryText("Summary text")
        return bigTextStyle
    }

    private fun createBigPictureStyle(): NotificationCompat.Style {
        val bigPictureStyle = NotificationCompat.BigPictureStyle()
        bigPictureStyle.setBigContentTitle("Big content title")
        bigPictureStyle.setSummaryText("Summary text")
        bigPictureStyle.bigPicture(notificationHelper.bitmap)
        return bigPictureStyle
    }

    private fun createInboxStyle(): NotificationCompat.Style {
        val inboxStyle = NotificationCompat.InboxStyle()
        val events = arrayOfNulls<String>(6)
        inboxStyle.setBigContentTitle("Big content title")
        inboxStyle.setSummaryText("Summary text")
        // Moves events into the expanded layout
        for (i in 0..4) {
            inboxStyle.addLine("$i: inbox style line")
        }
        return inboxStyle
    }

    companion object {
        private const val NOTIFICATION_ID_PLAIN = 0
        private const val NOTIFICATION_ID_INBOX = 1
        private const val NOTIFICATION_ID_BIG_PICTURE = 2
        private const val NOTIFICATION_ID_BIG_TEXT = 3
    }

}