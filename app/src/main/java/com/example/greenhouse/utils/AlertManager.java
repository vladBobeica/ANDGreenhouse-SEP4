package com.example.greenhouse.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.example.greenhouse.R;
import com.example.greenhouse.model.RecommendedMeasurementsModel;
import com.example.greenhouse.model.MeasurementModel;

public class AlertManager {

    public static final int INTERNET_PERMISSION_REQUEST_CODE = 100;

    private final String CHANNEL_ID = "alerts";
    private final String CHANNEL_NAME = "Alerts";
    private Context context;

    public AlertManager(Context context) {
        this.context = context;
        createNotificationChannel(); // Call this during initialization
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
            );

            NotificationManager manager = context.getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    public void checkAndSendAlerts(MeasurementModel liveMeasurements, RecommendedMeasurementsModel recommendedValues) {
        float liveTemperature = parseValue(liveMeasurements.getTemperature());
        float liveLight = parseValue(liveMeasurements.getLight());
        float liveHumidity = parseValue(liveMeasurements.getHumidity());

        float minTemperature = parseValue(recommendedValues.getMinTemperature());
        float maxTemperature = parseValue(recommendedValues.getMaxTemperature());
        float minLight = parseValue(recommendedValues.getMinLight());
        float maxLight = parseValue(recommendedValues.getMaxLight());
        float minHumidity = parseValue(recommendedValues.getMinHumidity());
        float maxHumidity = parseValue(recommendedValues.getMaxHumidity());

        if (liveTemperature < minTemperature) {
            sendAlert("Temperature is too low!");
        }
        if (liveTemperature > maxTemperature) {
            sendAlert("Temperature is too high!");
        }
        if (liveLight < minLight) {
            sendAlert("Light is too low!");
        }
        if (liveLight > maxLight) {
            sendAlert("Light is too high!");
        }
        if (liveHumidity < minHumidity) {
            sendAlert("Humidity is too low!");
        }
        if (liveHumidity > maxHumidity) {
            sendAlert("Humidity is too high!");
        }
    }

    private float parseValue(String value) {
        String numericPart = value.replace("°C", "").trim();
        try {
            return Float.parseFloat(numericPart);
        } catch (NumberFormatException e) {

            e.printStackTrace();
            return Float.NaN;
        }
    }


    private void sendAlert(String message) {
        if (context.checkSelfPermission(android.Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.baseline_notifications_24)
                    .setContentTitle("Alert")
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(101, builder.build());
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Permission Required");
            alertDialogBuilder.setMessage("This app requires permission to show alerts. Please grant the necessary permission.");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{android.Manifest.permission.INTERNET},
                            INTERNET_PERMISSION_REQUEST_CODE);
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
}
