package com.example.channel6game;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
//citation: code taken from lecture material and android studio documentation.
public class SmsServer extends BroadcastReceiver {
    private static int  recives=0;
    private static int incorrect_count=0;
    private static String[] winners= new String[50];
    private static int i=0;
    private static String answer="REPENT";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        System.out.println("Received SMS: ");
        Toast.makeText(context, "Message Received", Toast.LENGTH_LONG).show();
        SmsMessage[] recMsg = null;
        if (bundle != null)
        {
            Object[] pdus = (Object[]) bundle.get("pdus");
            recMsg = new SmsMessage[pdus.length];
            for (int i=0; i<recMsg.length; i++){
                String format = bundle.getString("format");
                recMsg[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                checkwin(recMsg[i].getMessageBody().toString(),recMsg[i].getOriginatingAddress());
            }

        }
        checkrecives();
    }
    private void addwinner(String num){
        this.winners[i] = num;
        this.i++;
    }
    private Boolean valid(String message){
        if(message.length()>=2){
            if(message.substring(0,2).equals("S6")){
                return true;
            }
        }
     return false;
    }
    private void checkvalid(String message){
        if(!valid(message)){
            this.incorrect_count++;
        }
    }
    private Boolean correct(String message){
        if(message.substring(2,8).equals(this.answer)){
            return true;
        }
        return false;
    }
    private void checkwin(String message,String phoneno){
        checkvalid(message);
        if(valid(message)&&correct(message)){
            addwinner(phoneno);
        }
    }
    private void checkrecives(){
        this.recives++;
        if(this.recives==8){
            System.out.println("incorrect count: "+this.incorrect_count);
            for(int k=0;k<this.i;k++){
                System.out.println(this.winners[k]);
            }

        }
    }

}
