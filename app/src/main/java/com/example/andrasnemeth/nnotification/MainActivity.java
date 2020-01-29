package com.example.andrasnemeth.nnotification;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity  extends AppCompatActivity {

    private LegacyNotifications legacyNotifications;
    private InlineReply inlineReply;
    private BundledNotifictions bundledNotifictions;
    private CustomViewNotifications customViewNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseNotificationHelper notificationHelper = new BaseNotificationHelper(this);
        legacyNotifications = new LegacyNotifications(notificationHelper);
        inlineReply = new InlineReply(notificationHelper);
        bundledNotifictions = new BundledNotifictions(notificationHelper);
        customViewNotifications = new CustomViewNotifications(notificationHelper);

        inlineReply.handleInlineReply(getIntent());
    }

    public void onPlainButtonClick(View view) {
        legacyNotifications.showPlainNotification();
    }

    public void onInboxStyleButtonClick(View view) {
        legacyNotifications.showInboxSytleNotification();
    }

    public void onBigPictureStyleButtonClick(View view) {
        legacyNotifications.showBigPictureStyleNotification();
    }

    public void onBigTextStyleButtonClick(View view) {
        legacyNotifications.showBigTextStyleNotification();
    }

    public void onInlineReplyButtonClick(View view) {
        inlineReply.showNotification();
    }

    public void onBundledButtonClick(View view) {
        bundledNotifictions.addNotificationToBundle();
    }

    public void onCustomViewButtonClick(View view) {
        customViewNotifications.show();
    }
}
