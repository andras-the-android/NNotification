package com.example.andrasnemeth.nnotification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.view.View;
import android.widget.Toast;

/**
 * Created by guni8 on 2016. 04. 03..
 */
public class InlineReply {

    private static final String KEY_TEXT_REPLY = "key_text_reply";
    private static final int NOTIFICATION_ID_INLINE_REPLY = 4;

    private BaseNotificationHelper notificationHelper;

    public InlineReply(BaseNotificationHelper notificationHelper) {
        this.notificationHelper = notificationHelper;
    }

    public void showNotification() {

        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("Reply")
                .build();

        // Create the reply action and add the remote input
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_launcher,
                        "Reply inline", notificationHelper.getPendingIntent())
                        .addRemoteInput(remoteInput)
                        .build();

        notificationHelper.showNotification(notificationHelper.createNotificationBuilder().addAction(action), NOTIFICATION_ID_INLINE_REPLY);
    }

    public void handleInlineReply(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            CharSequence message = "You've just sent the following message: " + remoteInput.getCharSequence(KEY_TEXT_REPLY);
            Toast.makeText(notificationHelper.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}
