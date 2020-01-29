package com.example.andrasnemeth.nnotification

import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

/**
 * Created by guni8 on 2016. 04. 03..
 */
class InlineReply(private val notificationHelper: BaseNotificationHelper) {
    fun showNotification() {
        val remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("Reply")
                .build()
        // Create the reply action and add the remote input
        val action = NotificationCompat.Action.Builder(R.drawable.ic_launcher,
                "Reply inline", notificationHelper.pendingIntent)
                .addRemoteInput(remoteInput)
                .build()
        notificationHelper.showNotification(notificationHelper.createNotificationBuilder().addAction(action), NOTIFICATION_ID_INLINE_REPLY)
    }

    fun handleInlineReply(intent: Intent?) {
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if (remoteInput != null) {
            val message: CharSequence = "You've just sent the following message: " + remoteInput.getCharSequence(KEY_TEXT_REPLY)
            Toast.makeText(notificationHelper.context, message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val KEY_TEXT_REPLY = "key_text_reply"
        private const val NOTIFICATION_ID_INLINE_REPLY = 4
    }

}