package com.example.andrasnemeth.nnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by guni8 on 2016. 04. 03..
 */
public class BaseNotificationHelper {

    private Context context;
    private Bitmap bitmap;

    public BaseNotificationHelper(Context context) {
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cannond);
    }

    public NotificationCompat.Builder createNotificationBuilder() {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Content title")
                        .setContentText("Content text")
                        .setLargeIcon(bitmap)
                        .setAutoCancel(true)
                        .setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        PendingIntent resultPendingIntent = getPendingIntent();
        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.addAction(R.drawable.ic_launcher, "First action", resultPendingIntent);
        mBuilder.addAction(R.drawable.ic_launcher, "Second action", resultPendingIntent);
        return mBuilder;
    }

    public PendingIntent getPendingIntent() {
        Intent resultIntent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT );
    }

    public void showNotification(NotificationCompat.Builder builder, int id) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, builder.build());
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Context getContext() {
        return context;
    }
}
