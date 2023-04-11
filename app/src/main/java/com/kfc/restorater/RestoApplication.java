package com.kfc.restorater;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class RestoApplication extends Application {
    private static final String CHANNEL_ID = "login_notification_channel";
    private static NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Login Notification", NotificationManager.IMPORTANCE_HIGH);
        notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

}
