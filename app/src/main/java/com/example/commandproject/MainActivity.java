package com.example.commandproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_SEND_SMS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(i);
            }
        });

        /////////////////////////////////////////////////////////////////////////////////
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                // Show an explanation to the user
                // This explanation will be shown only once
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("We need your permission to send SMS messages");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Request permission
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_SEND_SMS);
                    }
                });
                builder.show();
            } else {
                // No explanation needed, request permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_SEND_SMS);
            }
        } else {
            // Permission has already been granted
            // Send SMS message
            // ...
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted
                // Send SMS message
                // ...
            } else {
                // Permission has been denied
                // Show a message to the user
                Toast.makeText(this, "Permission to send SMS messages has been denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    // В этом примере PERMISSION_REQUEST_SEND_SMSконстанта используется для идентификации запроса разрешения.
//  Метод checkSelfPermissionиспользуется для проверки наличия разрешения на отправку SMS-сообщений.
// Если оно не предоставлено, shouldShowRequestPermissionRationaleметод используется для определения того,
// отказывал ли пользователь ранее в разрешении, и если да, то пользователю показывается объяснение.
//  В противном случае запрос разрешения выполняется с использованием requestPermissionsметода.
//
// Метод onRequestPermissionsResultиспользуется для обработки результата запроса разрешения.
// Если разрешение предоставлено, SMS-сообщение может быть отправлено. В противном случае пользователю
// показывается сообщение о том, что разрешение на отправку SMS-сообщений было отклонено
}