package com.ArduinoFactory.androidstudio.Notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.ArduinoFactory.androidstudio.R;
import com.ArduinoFactory.androidstudio.cours.Cours_Main;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

public class NotificationService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (!remoteMessage.getData().isEmpty()) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            SharedPreferences.Editor editor = getSharedPreferences("notif", MODE_PRIVATE).edit();
            editor.putString("notif1", remoteMessage.getNotification().getBody()).apply();
        }

        createNotification();
        createNotificationChannel();
    }

    private void createNotification() {
        Intent intent = new Intent(this, Cours_Main.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // ✅ Ajout du FLAG_IMMUTABLE ici
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "letunnel")
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("ArduinoFactory")
                .setContentText("Nouvelle notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(100, builder.build());
    }

    private void createNotificationChannel() {
        CharSequence name = "studentChannel";
        String description = "Channel for student notification";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("letunnel", name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    // ✅ Méthode recommandée pour gérer les changements de token FCM
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d(TAG, "Refreshed token: " + token);

        SharedPreferences.Editor editor = getSharedPreferences("notif", MODE_PRIVATE).edit();
        editor.putString("fcm_token", token).apply();

        // Tu pourrais aussi envoyer ce token à ton serveur ici
    }
}
