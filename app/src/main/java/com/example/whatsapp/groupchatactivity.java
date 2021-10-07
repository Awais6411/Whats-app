package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.whatsapp.Adapters.chatAdapter;
import com.example.whatsapp.databinding.ActivityGroupchatactivityBinding;
import com.example.whatsapp.models.messegesModels;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class groupchatactivity extends AppCompatActivity {
    ActivityGroupchatactivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupchatactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(groupchatactivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final ArrayList<messegesModels> messegesModels = new ArrayList<>();
        final String senderId = FirebaseAuth.getInstance().getUid();
        binding.ChatUserName.setText("Friend's Group");
        final chatAdapter adapter = new chatAdapter(messegesModels, this);
        binding.Chattrecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.Chattrecycler.setLayoutManager(layoutManager);
        database.getReference().child("Group Chat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messegesModels.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    messegesModels model = dataSnapshot.getValue(messegesModels.class);
                    messegesModels.add(model);
                }
               adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.sendim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message = binding.typingText.getText().toString();
                final messegesModels model = new messegesModels(senderId, message);
                model.setTimestamp(new Date().getTime());
                binding.typingText.setText("");
                database.getReference().child("Group Chat").push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });


            }
        });


    }
}