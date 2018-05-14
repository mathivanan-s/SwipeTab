package com.example.mathi.swipetab;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.concurrent.atomic.AtomicInteger;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Tab1 extends Fragment implements View.OnClickListener {
    EditText ed_title,ed_topic,ed_body;
    Button bt_submit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);
         ed_title=(EditText)view.findViewById(R.id.title);
         ed_topic=(EditText)view.findViewById(R.id.topic);
         ed_body=(EditText)view.findViewById(R.id.body);
        bt_submit=(Button)view.findViewById(R.id.submit);
        bt_submit.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id==R.id.submit){
            insertUser();
        }
    }
    private void insertUser() {
        FirebaseMessaging fm = FirebaseMessaging.getInstance();
        AtomicInteger msgId = new AtomicInteger();
        fm.send(new RemoteMessage.Builder("751592734274" + "@gcm.googleapis.com")
                .setMessageId(Integer.toString(msgId.incrementAndGet()))
                .addData("my_message", "Hello World")
                .addData("my_action","SAY_HELLO")
                .build());

    }
}