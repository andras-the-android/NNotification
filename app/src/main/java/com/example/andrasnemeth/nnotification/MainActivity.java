package com.example.andrasnemeth.nnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity  extends AppCompatActivity {

    public static final int mId = 666;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cannond);
    }

    public void onPlainButtonClick(View view) {
        showNotification(createNotificationBuilder());
    }

    public void onInboxStyleButtonClick(View view) {
        showNotification(createNotificationBuilder().setStyle(createInboxStyle()));
    }

    public void onBigPictureStyleButtonClick(View view) {
        showNotification(createNotificationBuilder().setStyle(createBigPictureStyle()));
    }

    public void onBigTextStyleButtonClick(View view) {
        showNotification(createNotificationBuilder().setStyle(createBigTextStyle()));
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
        bigPictureStyle.bigPicture(bitmap);
        return bigPictureStyle;
    }

    @NonNull
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

    @NonNull
    private NotificationCompat.Builder createNotificationBuilder() {
        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT );

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        //Manadatory items
                        //------
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Content title")
                        .setContentText("Content text")
                        //------
                        .setLargeIcon(bitmap)
                        //dismiss notification atrer the user tapped on it
                        .setAutoCancel(true)
                //this generates an indeterminate progress bar
                //.setProgress(0, 0, true)
                ;
        //alternative configuration way
        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.addAction(R.drawable.ic_launcher, "First", resultPendingIntent);
        mBuilder.addAction(R.drawable.ic_launcher, "Second", resultPendingIntent);
        return mBuilder;
    }

    private void showNotification(NotificationCompat.Builder mBuilder) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(mId, mBuilder.build());
    }
}
