package com.example.projetlicence.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.projetlicence.Adapter.ProductsClientAdapter;
import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ListProductFragment extends Fragment {
    EditText editText_Search;
    RecyclerView recyclerView;
    ProductsClientAdapter adapter;
    List<Products> listAll;
    List<Products> mylist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_list_product, container, false);
        editText_Search=root.findViewById(R.id.et_Search);
        recyclerView=root.findViewById(R.id.recycle_products_client);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        DatabaseReference myref;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myref = database.getReference("products");
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
                adapter=new ProductsClientAdapter(getContext(),mylist);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }

        });

        editText_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String textSearch= editText_Search.getText().toString();
                if(textSearch.isEmpty()){
                    adapter=new ProductsClientAdapter(getActivity(),listAll);
                    recyclerView.setAdapter(adapter);
                }else{
                    mylist.clear();
                    for (Products item:listAll) {
                        if(item.getName_product().contains(textSearch)){
                            mylist.add(item);
                        }
                    }
                    adapter=new ProductsClientAdapter(getActivity(),mylist);
                    recyclerView.setAdapter(adapter);
                }}
        });
        return root;
    }
}