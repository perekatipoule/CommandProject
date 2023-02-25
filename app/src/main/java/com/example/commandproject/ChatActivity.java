package com.example.commandproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;


public class ChatActivity extends AppCompatActivity implements View.OnClickListener{
    TextView backWord;
    ImageButton backButton;
    ImageButton sendButton;
    TextView contactName;
    TextView contactNumber;
    String contactNumberStr;
    EditText messageBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        contactName = findViewById(R.id.contactName);
        contactNumber = findViewById(R.id.contactNumber);
        backWord = findViewById(R.id.chat_back_word);
        backButton = findViewById(R.id.chat_back_button);
        sendButton = findViewById(R.id.sendButton);
        messageBox = findViewById(R.id.messageBox);

        contactName.setText("Аркадій Вікторович Овдієнко");
        contactNumber.setText("+380631233135");
        contactNumberStr = contactNumber.getText().toString();
        backWord.setOnClickListener(this);
        backButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);

        /////////////////////////////////////////////////////////////


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.chat_back_word:
            case R.id.chat_back_button:
                finish();
                break;
            case R.id.sendButton:
                String message = messageBox.getText().toString();
                if (message.length() > 0) {
                    sentMessage(message);
                }
                break;
        }
    }

    public void sentMessage(String message) {
        String timestamp;
        SmsManager smsManager;
        try {
            smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(contactNumberStr, null, message, null, null);
            timestamp = DateFormat.getTimeInstance().format(new Date());
            Toast.makeText(getApplicationContext(), "Message Sent!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Message failed to send.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}