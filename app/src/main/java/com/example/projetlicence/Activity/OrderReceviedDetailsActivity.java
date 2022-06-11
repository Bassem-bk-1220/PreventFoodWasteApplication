package com.example.projetlicence.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetlicence.Adapter.OrderReceviedAdapter;
import com.example.projetlicence.Adapter.ProductsOrdersRecivedAdapter;
import com.example.projetlicence.Modele.Commande;
import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.Modele.Users;
import com.example.projetlicence.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OrderReceviedDetailsActivity extends AppCompatActivity {
    RecyclerView recyclerView_product_recevied;
    ProductsOrdersRecivedAdapter adapter;
    ArrayList<Products> listorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_recevied_details);

        recyclerView_product_recevied=findViewById(R.id.recycle_product_orders);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView_product_recevied.setLayoutManager(linearLayoutManager);
        recyclerView_product_recevied.setHasFixedSize(true);
        DatabaseReference myref;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String id_commande=getIntent().getStringExtra("id_commande");
        myref= database.getReference("commande").child(id_commande).child("listProduct");
        listorder=new ArrayList<>();

        myref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataValues:snapshot.getChildren()){
                    Products product = dataValues.getValue(Products.class);
                    listorder.add(product);
                }

                adapter=new ProductsOrdersRecivedAdapter(OrderReceviedDetailsActivity.this,listorder);
                    recyclerView_product_recevied.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myref= database.getReference("commande").child(id_commande).child("id_user");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String id_user=snapshot.getValue(String.class);
                DatabaseReference myref= database.getReference("users").child(id_user);
                myref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        Users user=snapshot.getValue(Users.class);

                        final String pic_profil=user.getProfileimage();
                        ImageView imageView_logo_client=findViewById(R.id.img_logo_client);
                        TextView textView_name_client=findViewById(R.id.txtv_name_client);
                        TextView textView_phone_number=findViewById(R.id.txtv_phone_number);


                        if(pic_profil != null && !pic_profil.isEmpty()){
                            Picasso.get().load(pic_profil).placeholder(R.drawable.profile_picture).into(imageView_logo_client);
                        }
                        textView_name_client.setText(user.getFullname());
                        textView_phone_number.setText(user.getPhone());

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

}