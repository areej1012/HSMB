package com.example.hsmb;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class Notification extends ContextWrapper {
    private static final String CHANNEL_NAME = "Main Notifications";
    private static final String CHANNEL_DESCRIPTION = "Notification description";
    private static final String CHANNEL_ID = "MyNotificationChannel";
    private static NotificationManager notificationManager;
    private Context base;
    Uri notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    public Notification(Context base) {
        super(base);
        this.base = base;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }


   @RequiresApi(api = Build.VERSION_CODES.O)
   public void createChannels() {

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(CHANNEL_DESCRIPTION);
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationChannel.canShowBadge();
            notificationChannel.setLockscreenVisibility(Notification.BIND_IMPORTANT);
            notificationChannel.setLightColor(Color.RED);
            getNotificationManager().createNotificationChannel(notificationChannel);

    }
    public NotificationManager getNotificationManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return notificationManager;
    }
    public  NotificationCompat.Builder  notify(String title, String message) {
        return new NotificationCompat.Builder(base, CHANNEL_ID)
        .setContentTitle(title)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentText(message)
        .setSound(notificationUri)
        .setStyle(new NotificationCompat.BigTextStyle().bigText(message));

    }
}

