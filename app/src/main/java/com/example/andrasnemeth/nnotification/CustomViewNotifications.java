package com.example.andrasnemeth.nnotification;

import android.app.Notification;
import android.widget.RemoteViews;

/**
 * Created by guni8 on 2016. 04. 04..
 */
public class CustomViewNotifications {

    private static final int NOTIFICATION_ID_CUSTOM = 4;

    private BaseNotificationHelper notificationHelper;

    public CustomViewNotifications(BaseNotificationHelper notificationHelper) {
        this.notificationHelper = notificationHelper;
    }

    public void show() {
        RemoteViews remoteViews = new RemoteViews(notificationHelper.getContext().getPackageName(), R.layout.notification_custom);
        remoteViews.setTextViewText(R.id.content_title, "This is the title of the custom view notification");

        RemoteViews remoteViewsBig = new RemoteViews(notificationHelper.getContext().getPackageName(), R.layout.notification_big_custom);
        remoteViewsBig.setTextViewText(R.id.content_title, "This is the title of the big custom view notification");
        remoteViewsBig.setTextViewText(R.id.content_text, "This is the text of the big custom view notification");

        Notification.Builder builder = new Notification.Builder(notificationHelper.getContext())
            .setSmallIcon(R.drawable.ic_launcher)
            .setAutoCancel(true)
            .setCustomContentView(remoteViews)
            .setCustomBigContentView(remoteViewsBig)
            .setStyle(new Notification.DecoratedCustomViewStyle());

        notificationHelper.showNotification(builder, NOTIFICATION_ID_CUSTOM);
    }

}
