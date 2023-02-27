package com.example.commandproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton backButton, sendButton;
    TextView contactName, contactNumber, backWord, readyNote1, readyNote2;
    String contactNumberStr, sendingMessage;
    EditText messageBox;
    List<Message> messageList;
    SmsManager smsManager;
    RecyclerView messagesRecyclerView;
    MessagesAdapter messagesAdapter;
    Date date;
    BroadcastReceiver smsReceiver;


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
        readyNote1 = findViewById(R.id.ready_note_1);
        readyNote2 = findViewById(R.id.ready_note_2);

        contactName.setText("Аркадій Вікторович Овдієнко");
        contactNumber.setText("+380683813805");
        contactNumberStr = contactNumber.getText().toString();
        backWord.setOnClickListener(this);
        backButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);

        // Recycler view handling
        messageList = new ArrayList<>();
        messagesRecyclerView = findViewById(R.id.recyclerView_chat_log);
        smsManager = SmsManager.getDefault();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        messagesRecyclerView.setLayoutManager(linearLayoutManager);
        messagesAdapter=new MessagesAdapter(ChatActivity.this, messageList);
        messagesRecyclerView.setAdapter(messagesAdapter);

        //receiveMessages
        smsReceiver = new SmsReceiver(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.chat_back_word:
            case R.id.chat_back_button:
                finish();
                break;
            case R.id.sendButton:
                sendMessage();
                break;
        }
    }

    public void sendMessage() {
        sendingMessage = messageBox.getText().toString();
        date = new Date();
        try { //
        smsManager.sendTextMessage(contactNumberStr, null, sendingMessage, null, null);//
        messageList.add(new Message(sendingMessage, true, date.getTime()));
        messagesAdapter.notifyItemInserted(messageList.size() - 1);
        messageBox.setText(null);
        // Removing ready note
        if (messageList.size() > 0) {
            readyNote1.setVisibility(View.GONE);
            readyNote2.setVisibility(View.GONE);
        }
            Toast.makeText(getApplicationContext(), "Message Sent!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Message failed to send.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    public void receiveMessage(String message) {
        date = new Date();
        messageList.add(new Message(message, false, date.getTime()));
        messagesAdapter.notifyItemInserted(messageList.size() - 1);
    }

    @Override
    public void onStart() {
        super.onStart();
        messagesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(messagesAdapter!=null)
        {
            messagesAdapter.notifyDataSetChanged();
        }
    }


}