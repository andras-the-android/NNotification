package com.example.andrasnemeth.nnotification;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import android.view.View;

/**
 * Created by guni8 on 2016. 04. 03..
 */
public class LegacyNotifications {

    private static final int NOTIFICATION_ID_PLAIN = 0;
    private static final int NOTIFICATION_ID_INBOX = 1;
    private static final int NOTIFICATION_ID_BIG_PICTURE = 2;
    private static final int NOTIFICATION_ID_BIG_TEXT = 3;

    private BaseNotificationHelper notificationHelper;

    public LegacyNotifications(BaseNotificationHelper notificationHelper) {
        this.notificationHelper = notificationHelper;
    }

    public void showPlainNotification() {
        notificationHelper.showNotification(notificationHelper.createNotificationBuilder(), NOTIFICATION_ID_PLAIN);
    }

    public void showInboxSytleNotification() {
        NotificationCompat.Builder notificationBuilder = notificationHelper.createNotificationBuilder();
        notificationBuilder.setStyle(createInboxStyle());
        notificationHelper.showNotification(notificationBuilder, NOTIFICATION_ID_INBOX);
    }

    public void showBigPictureStyleNotification() {
        NotificationCompat.Builder notificationBuilder = notificationHelper.createNotificationBuilder();
        notificationBuilder.setStyle(createBigPictureStyle());
        notificationHelper.showNotification(notificationBuilder, NOTIFICATION_ID_BIG_PICTURE);
    }

    public void showBigTextStyleNotification() {
        NotificationCompat.Builder notificationBuilder = notificationHelper.createNotificationBuilder();
        notificationBuilder.setStyle(createBigTextStyle());
        notificationHelper.showNotification(notificationBuilder, NOTIFICATION_ID_BIG_TEXT);
    }

    private NotificationCompat.Style createBigTextStyle() {
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText("Big text");
        bigTextStyle.setBigContentTitle("Big content tite");
        bigTextStyle.setSummaryText("Summary text");
        return bigTextStyle;
    }

    private NotificationCompat.Style createBigPictureStyle() {
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.setBigContentTitle("Big content title");
        bigPictureStyle.setSummaryText("Summary text");
        bigPictureStyle.bigPicture(notificationHelper.getBitmap());
        return bigPictureStyle;
    }

    private NotificationCompat.Style createInboxStyle() {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = new String[6];
        inboxStyle.setBigContentTitle("Big content title");
        inboxStyle.setSummaryText("Summary text");

        // Moves events into the expanded layout
        for (int i=0; i < 5; i++) {
            inboxStyle.addLine(i + ": inbox style line");
        }
        return inboxStyle;
    }
}
