package com.example.automaticgrocery.data.BroadcastReceiver;



import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.Main.MainActivity;
import com.example.automaticgrocery.data.Items.ExpiredItem;
import com.example.automaticgrocery.data.Items.FillItem;
import com.example.automaticgrocery.data.Repository.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {

    //var for channel id
    private static final String CHANNEL_ID = "CHANNEL_ID";

    //calendar for alarm
    private Calendar calendar;

    //repository for communication
    private Repository repository;

    @SuppressLint("ScheduleExactAlarm")
    @Override
    public void onReceive(Context context, Intent intent) {
        repository = new Repository(context);

        calendar =  Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.DAY_OF_MONTH));

        // Create or get the notification channel
        createNotificationChannel(context);

        // Create an explicit intent for an Activity in your app
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Create the PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        //collect products data
        int x = 0;
        int y = 0;
        Cursor cursor = repository.getProductsByCategory("הכל");
        cursor.moveToFirst();
        int l = cursor.getCount();
        for (int i = 0; i < l; i++)
        {
            if(isExpired(cursor.getString(5)))
                x++;

            if (isFillNeeded(cursor.getInt(7),cursor.getInt(3)))
                y++;

            cursor.moveToNext();
        }


        // Create the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.app_icon)
                .setContentTitle("Product Alert!!!")
                .setContentText(x + " products are expired and  + " + y + " products are need a refill.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true); // Automatically removes the notification when clicked

        // Show the notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long intervalMillis =   60 * 60 * 1000; // 1 hour in milliseconds
        long triggerTime = System.currentTimeMillis() + intervalMillis;
        pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        if (alarmManager != null) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
        }
    }

    // Create the notification channel
    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = notificationManager.getNotificationChannel(CHANNEL_ID);
            if (channel == null) {
                channel = new NotificationChannel(
                        CHANNEL_ID,
                        "Notification Channel",
                        NotificationManager.IMPORTANCE_HIGH
                );
                channel.setDescription("Channel Description");
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    //check if product is expired
    public boolean isExpired(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String today = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)) + "/" + calendar.get(Calendar.YEAR);
        try {
            Date date1 = dateFormat.parse(today);
            Date date2 = dateFormat.parse(date);

            long differenceInMillis = date2.getTime() - date1.getTime();
            return differenceInMillis < 0;
        } catch (Exception e) {
            return false;
        }
    }

    //check if product need a fill
    public boolean isFillNeeded(int target,int current){
        return current / target < 0.60;
    }
}
