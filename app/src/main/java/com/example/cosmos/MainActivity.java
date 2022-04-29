package com.example.cosmos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText wishes;
    Button sendWishes1;
    Button sendWishes2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        clickListener();
    }

    public void init() {
        wishes = findViewById(R.id.et_wishes);
        sendWishes1 = findViewById(R.id.btn_send1);
        sendWishes2 = findViewById(R.id.btn_send2);
    }
    public void  clickListener(){
        sendWishes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendWishes1.setVisibility(View.INVISIBLE);
                wishes.setVisibility(View.VISIBLE);
                wishes.setCursorVisible(true);
                sendWishes2.setVisibility(View.VISIBLE);
            }
        });

        sendWishes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    @SuppressLint("IntentReset")
    public void sendEmail() {
//        String[] TO = {};
//        String[] CC = {};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_EMAIL, TO);
//        intent.putExtra(Intent.EXTRA_CC, CC);
        intent.putExtra(Intent.EXTRA_SUBJECT, "My wishes before flight");
        intent.putExtra(Intent.EXTRA_TEXT, wishes.getText().toString());
        Log.i("TAG", "!! Function sendEmail run");

        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
            finish();
            Log.i("Finish", "!! LettersApps open for send email");
        } catch (android.content.ActivityNotFoundException e){
            Toast.makeText(MainActivity.this, "There is no email client installed.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}



