package com.example.andrasnemeth.nnotification

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var legacyNotifications: LegacyNotifications? = null
    private var inlineReply: InlineReply? = null
    private var bundledNotifictions: BundledNotifictions? = null
    private var customViewNotifications: CustomViewNotifications? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val notificationHelper = BaseNotificationHelper(this)
        notificationHelper.createNotificationChannel()
        legacyNotifications = LegacyNotifications(notificationHelper)
        inlineReply = InlineReply(notificationHelper)
        bundledNotifictions = BundledNotifictions(notificationHelper)
        customViewNotifications = CustomViewNotifications(notificationHelper)
        inlineReply!!.handleInlineReply(intent)
    }

    fun onPlainButtonClick(view: View?) {
        legacyNotifications!!.showPlainNotification()
    }

    fun onInboxStyleButtonClick(view: View?) {
        legacyNotifications!!.showInboxSytleNotification()
    }

    fun onBigPictureStyleButtonClick(view: View?) {
        legacyNotifications!!.showBigPictureStyleNotification()
    }

    fun onBigTextStyleButtonClick(view: View?) {
        legacyNotifications!!.showBigTextStyleNotification()
    }

    fun onInlineReplyButtonClick(view: View?) {
        inlineReply!!.showNotification()
    }

    fun onBundledButtonClick(view: View?) {
        bundledNotifictions!!.addNotificationToBundle()
    }

    fun onCustomViewButtonClick(view: View?) {
        customViewNotifications!!.show()
    }
}