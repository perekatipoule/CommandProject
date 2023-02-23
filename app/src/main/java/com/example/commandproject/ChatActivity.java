package com.example.commandproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{
    TextView backWord;
    ImageButton backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        TextView contactName = findViewById(R.id.contactName);
        TextView contactNumber = findViewById(R.id.contactNumber);
        contactName.setText("Аркадій Вікторович Овдієнко");
        contactNumber.setText("+380683812405");
        backWord = findViewById(R.id.chat_back_word);
        backButton = findViewById(R.id.chat_back_button);
        backWord.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.chat_back_word:
            case R.id.chat_back_button:
                finish();
        }
    }
}