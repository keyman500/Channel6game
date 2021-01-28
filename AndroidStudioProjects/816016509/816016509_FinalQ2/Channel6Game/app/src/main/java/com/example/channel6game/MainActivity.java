package com.example.channel6game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//citation: code taken from lecture material and andriod studio documentation.
public class MainActivity extends AppCompatActivity {
    private static final String phoneNo = "5554";
    EditText smsans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smsans =  findViewById(R.id.editTextTextPersonName);
        SmsServer server = new SmsServer();
    }

    public void sendSMS(View view) {

        String text = smsans.getText().toString();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, text, null, null);
            Toast.makeText(getApplicationContext(), "Message sent too game server",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Failed too send message",
                    Toast.LENGTH_LONG).show();
        }

    }
}