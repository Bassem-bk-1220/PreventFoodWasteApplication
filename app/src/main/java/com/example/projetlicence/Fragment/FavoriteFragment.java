package com.example.projetlicence.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetlicence.Adapter.ProductsFavoriteAdapter;
import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    ProductsFavoriteAdapter adapter;
    List<Products> listAll;
    List<Products> mylist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView=root.findViewById(R.id.recycle_products_favorite);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DatabaseReference myref;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
       FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
       FirebaseUser user = mFirebaseAuth.getCurrentUser();
        myref = database.getReference("favorites").child(user.getUid().toString());
        listAll=new ArrayList<>();
        mylist=new ArrayList<>();
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot dataValues:dataSnapshot.getChildren()){
                    Products product = dataValues.getValue(Products.class);
                    mylist.add(product);
                    listAll.add(product);
                }
                adapter=new ProductsFavoriteAdapter(getContext(),mylist);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }

        });
        return root;
    }

}