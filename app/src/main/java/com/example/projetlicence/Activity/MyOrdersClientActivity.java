package com.example.projetlicence.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projetlicence.Adapter.MyOrdersAdapter;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MyOrdersClientActivity extends AppCompatActivity {
    RecyclerView recyclerView_myorders;
    MyOrdersAdapter adapter;
    TextView textView_validate;
    ArrayList<Products> listAll;
    ArrayList<Products> mylist;
    Products product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders_client);
        recyclerView_myorders=findViewById(R.id.recycle_myorders);
        textView_validate=findViewById(R.id.txtv_validate);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView_myorders.setLayoutManager(linearLayoutManager);
        recyclerView_myorders.setHasFixedSize(true);
        DatabaseReference myref;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        myref = database.getReference("cart").child(user.getUid().toString());
        listAll=new ArrayList<>();
        mylist=new ArrayList<>();

        myref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listAll.clear();
                mylist.clear();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot dataValues:dataSnapshot.getChildren()){
                     product = dataValues.getValue(Products.class);
                    mylist.add(product);
                    listAll.add(product);
                }
                adapter=new MyOrdersAdapter(MyOrdersClientActivity.this,mylist);
                recyclerView_myorders.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }

        });
        textView_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference().child("commande");
                String key=databaseReference.push().getKey();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String CurrentUserID = mAuth.getCurrentUser().getUid();
                databaseReference.child(key).child("id_user").setValue(CurrentUserID);
                databaseReference.child(key).child("id_commande").setValue(key);
                Long tsLong = System.currentTimeMillis() / 1000;
                String time = tsLong.toString();
                databaseReference.child(key).child("nr_commande").setValue(time);
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss aaa z");
                String dateTime = simpleDateFormat.format(calendar.getTime()).toString();
                databaseReference.child(key).child("date").setValue(dateTime);

                //double prix=0;
                //double prixTotal=Double.parseDouble(product.getPrix())+ prix;
                //databaseReference.child(key).child("prix").setValue(String.valueOf(prixTotal));
                for(Products product:mylist){
                    databaseReference.child(key).child("listProduct").child(product.getId_product()).setValue(product);

                }
                database.getReference("cart").child(user.getUid().toString()).removeValue();



            }
        });
    }
}