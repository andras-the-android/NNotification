package com.example.andrasnemeth.nnotification;

import android.support.v4.app.NotificationCompat;

/**
 * Created by guni8 on 2016. 04. 03..
 */
public class BundledNotifictions {

    private static final String KEY_NOTIFICATION_GROUP = "KEY_NOTIFICATION_GROUP";
    private static final int NOTIFICATION_ID_GROUP_SUMMARY = 666;

    private BaseNotificationHelper notificationHelper;

    private int notificationCounter = 0;

    public BundledNotifictions(BaseNotificationHelper notificationHelper) {
        this.notificationHelper = notificationHelper;
    }

    public void addNotificationToBundle() {
        NotificationCompat.Builder notificationBuilder = notificationHelper.createNotificationBuilder();
        if (notificationCounter == 1) {
            addGroupSummaryNotification();
        }
        notificationBuilder.setGroup(KEY_NOTIFICATION_GROUP);
        notificationHelper.showNotification(notificationBuilder, notificationCounter);
        ++notificationCounter;
    }

    public void addGroupSummaryNotification() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(notificationHelper.getContext())
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("This is the title of the group")
                .setGroupSummary(true)
                .setGroup(KEY_NOTIFICATION_GROUP);
        notificationHelper.showNotification(notificationBuilder, NOTIFICATION_ID_GROUP_SUMMARY);
    }
}
