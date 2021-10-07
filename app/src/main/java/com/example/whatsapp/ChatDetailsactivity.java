package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.whatsapp.Adapters.chatAdapter;
import com.example.whatsapp.databinding.ActivityChatDetailsactivityBinding;
import com.example.whatsapp.models.messegesModels;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailsactivity extends AppCompatActivity {
    ActivityChatDetailsactivityBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailsactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        String senderid = auth.getUid();
        String recieveid = getIntent().getStringExtra("userId");
        String userName = getIntent().getStringExtra("userName");
        String profilePic = getIntent().getStringExtra("profilePic");
        binding.ChatUserName.setText(userName);
        Picasso.get().load(profilePic).placeholder(R.drawable.ic_profile).into(binding.profileImage);
        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatDetailsactivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final ArrayList<messegesModels> messegesModels = new ArrayList<>();
        final chatAdapter chatAdapter = new chatAdapter(messegesModels, this);
        binding.Chattrecycler.setAdapter(chatAdapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.Chattrecycler.setLayoutManager(layoutManager);

       final String senderRoom=senderid+recieveid;
       final String receiverRoom=recieveid+senderid;

       database.getReference().child("chats").child(senderRoom)
               .addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       messegesModels.clear();
                       for(DataSnapshot snapshot1:snapshot.getChildren())
                       {
                           messegesModels model=snapshot1.getValue(messegesModels.class);
                           messegesModels.add(model);

                       }
                       chatAdapter.notifyDataSetChanged();

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
















        binding.sendim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              String message =binding.typingText.getText().toString();
              final messegesModels model=new messegesModels(senderid,message);
              model.setTimestamp(new Date().getTime());
              binding.typingText.setText("");
              database.getReference().child("chats").child(senderRoom).push()
                      .setValue(model)
                      .addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void aVoid) {

                      database.getReference().child("chats").child(receiverRoom).push()
                              .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                          @Override
                          public void onSuccess(Void aVoid) {

                          }
                      });

                  }
              });

            }
        });

    }
}