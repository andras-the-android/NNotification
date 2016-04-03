package com.example.andrasnemeth.nnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity  extends AppCompatActivity {

    // Key for the string that's delivered in the action's intent
    private static final String KEY_TEXT_REPLY = "key_text_reply";

    public static final int mId = 666;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cannond);
        handleInlineReply(getIntent());
    }

    private void handleInlineReply(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            CharSequence message = "You've just sent the following message: " + remoteInput.getCharSequence(KEY_TEXT_REPLY);
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
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

    public void onInlineReplyButtonClick(View view) {

        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("Reply")
                .build();

        // Create the reply action and add the remote input
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_launcher,
                        "Reply inline", getPendingIntent())
                        .addRemoteInput(remoteInput)
                        .build();

        showNotification(createNotificationBuilder().addAction(action));
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
        PendingIntent resultPendingIntent = getPendingIntent();
        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.addAction(R.drawable.ic_launcher, "First action", resultPendingIntent);
        mBuilder.addAction(R.drawable.ic_launcher, "Second action", resultPendingIntent);
        return mBuilder;
    }

    private PendingIntent getPendingIntent() {
        Intent resultIntent = new Intent(this, MainActivity.class);
        return PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT );
    }

    private void showNotification(NotificationCompat.Builder mBuilder) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(mId, mBuilder.build());
    }
}
