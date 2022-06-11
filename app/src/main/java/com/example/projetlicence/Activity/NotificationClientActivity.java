package com.example.projetlicence.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.example.projetlicence.Adapter.NotificationClientAdapter;
import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationClientActivity extends AppCompatActivity {
    RecyclerView recyclerView_client;
    NotificationClientAdapter adapter;
    ArrayList<Products> listAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_client);
        recyclerView_client=findViewById(R.id.recycle_notification_client);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView_client.setLayoutManager(linearLayoutManager);
        recyclerView_client.setHasFixedSize(true);
        DatabaseReference myref;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myref= database.getReference("products");
        listAll=new ArrayList<>();

        myref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listAll.clear();
                for(DataSnapshot dataValues:snapshot.getChildren()){
                    Products products = dataValues.getValue(Products.class);
                    listAll.add(products);
                }
                adapter=new NotificationClientAdapter(NotificationClientActivity.this,listAll);
                recyclerView_client.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}