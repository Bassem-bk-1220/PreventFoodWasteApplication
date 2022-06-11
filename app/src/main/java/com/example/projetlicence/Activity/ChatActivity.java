package com.example.projetlicence.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetlicence.Adapter.ChatAdapter;
import com.example.projetlicence.Modele.MessageUser;
import com.example.projetlicence.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView chattingRecyclerView;
    private ChatAdapter chatAdapter;
    List<MessageUser> mylist;
    DatabaseReference databaseReference;
    String recevier;
    String sender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final ImageView backbtn = findViewById(R.id.backBtn);
        final TextView nametv = findViewById(R.id.name);
        final EditText messageEdittext = findViewById(R.id.messageEdittext);
        final ImageView profilePic = findViewById(R.id.profilePic);
        final ImageView sendBtn = findViewById(R.id.sendBtn);
        chattingRecyclerView = findViewById(R.id.chattingRecycleView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        chattingRecyclerView.setLayoutManager(linearLayoutManager);
        chattingRecyclerView.setHasFixedSize(true);

        String id_user=getIntent().getStringExtra("id_user");

        String fullname=getIntent().getStringExtra("fullname");
        String profileimage=getIntent().getStringExtra("profileimage");

        nametv.setText(fullname);
        Picasso.get().load(profileimage).placeholder(R.drawable.profile_picture).into(profilePic);


        sendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String message_user = messageEdittext.getText().toString();
                Long tsLong = System.currentTimeMillis() / 1000;
                String time = tsLong.toString();

                MessageUser message = new MessageUser(sender, recevier, message_user, time);
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                String key = databaseReference.push().getKey();
                database.getReference("messages")
                        .child(sender).child(recevier).child(key).setValue(message);
                database.getReference("messages")
                        .child(recevier).child(sender).child(key).setValue(message);

                messageEdittext.setText("");


            }
        });
        mylist = new ArrayList<>();
         sender = FirebaseAuth.getInstance().getCurrentUser().getUid();
         recevier = id_user;
        databaseReference = FirebaseDatabase.getInstance().getReference("messages").child(sender).child(recevier);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot dataValues : dataSnapshot.getChildren()) {
                    MessageUser messageUser = dataValues.getValue(MessageUser.class);
                    mylist.add(messageUser);
                }
                chatAdapter = new ChatAdapter(ChatActivity.this, mylist);
                chattingRecyclerView.setAdapter(chatAdapter);


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }


}