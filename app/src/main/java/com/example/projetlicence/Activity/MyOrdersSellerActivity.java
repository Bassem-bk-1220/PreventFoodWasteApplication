package com.example.projetlicence.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.example.projetlicence.Adapter.MyOrdersAdapter;
import com.example.projetlicence.Adapter.OrderReceviedAdapter;
import com.example.projetlicence.Modele.Commande;
import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.Modele.Users;
import com.example.projetlicence.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyOrdersSellerActivity extends AppCompatActivity {
    RecyclerView recyclerView_myorders_seller;
    OrderReceviedAdapter adapter;
    ArrayList<Commande> listAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders_seller);
        recyclerView_myorders_seller=findViewById(R.id.recycle_myorders);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView_myorders_seller.setLayoutManager(linearLayoutManager);
        recyclerView_myorders_seller.setHasFixedSize(true);
        DatabaseReference myref;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myref= database.getReference("commande");
        listAll=new ArrayList<>();

        myref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listAll.clear();
                for(DataSnapshot dataValues:snapshot.getChildren()){
                    Commande commande = dataValues.getValue(Commande.class);
                    listAll.add(commande);
                }
                adapter=new OrderReceviedAdapter(MyOrdersSellerActivity.this,listAll);
                recyclerView_myorders_seller.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}