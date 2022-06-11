package com.example.projetlicence.Fragment;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.Adapter.NotificationSellerAdapter;
import com.example.projetlicence.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class NotificationFragment extends Fragment {
    RecyclerView recyclerView;
    NotificationSellerAdapter adapter;
    ArrayList<Products> listAll;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView=root.findViewById(R.id.recycle_notification_today);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
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
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        Calendar calendar = Calendar.getInstance();
                        Date myDate = dateFormat.parse(products.getDate_expiration());
                        Date newDate = new Date(myDate.getTime() - 604800000L);
                        Date current=calendar.getTime();

                        if(!(newDate.before(current))){
                            listAll.add(products);
                            Intent intent = new Intent(getContext(), NotificationFragment.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                }
                adapter=new NotificationSellerAdapter(getActivity(),listAll);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return root;
    }
}