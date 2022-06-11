package com.example.projetlicence.Fragment;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetlicence.Adapter.ProductsAdapter;
import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.Ui.MyAlaram;
import com.example.projetlicence.R;
import com.example.projetlicence.Activity.AddProductActivity;
import com.example.projetlicence.Activity.DetectionInformationProductActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    ImageView imageView_no_product,imageView_update_product;
    TextView textView_No_Product_Found,textView_Add_product,
            textView_btn_add_product;
    RecyclerView recyclerView;
    ProductsAdapter adapter;
    List<Products> listAll;
    List<Products> mylist;
    final static int RQS_1 = 1;
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_home, container, false);
        imageView_no_product=root.findViewById(R.id.image_no_product);
        textView_No_Product_Found=root.findViewById(R.id.tv_No_Product_Found);
        textView_Add_product=root.findViewById(R.id.Add_product);
        textView_btn_add_product=root.findViewById(R.id.btn_add_product);
        //imageView_update_product=root.findViewById(R.id.btn_update_product);

        //createNotificationChannel();
        textView_Add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), AddProductActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        textView_btn_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), DetectionInformationProductActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        recyclerView=root.findViewById(R.id.recycle_products);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        DatabaseReference myref;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myref = database.getReference("products");
        listAll=new ArrayList<>();
        mylist=new ArrayList<>();

        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser seller = mFirebaseAuth.getCurrentUser();
        myref.addValueEventListener(new ValueEventListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                   mylist.clear();

                    for (DataSnapshot dataValues : dataSnapshot.getChildren()) {
                        Products product = dataValues.getValue(Products.class);
                        if(product.getId_seller().equals(seller.getUid())){
                            mylist.add(product);
                            listAll.add(product);
                        }

                    }
                    if(mylist.size()!=0 ) {
                    recyclerView.setVisibility(View.VISIBLE);

                        for(Products item : mylist){
                        try {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Calendar calendar = Calendar.getInstance();
                            Date myDate = dateFormat.parse(item.getDate_expiration());
                            Date newDate = new Date(myDate.getTime() - 604800000L);
                            Date current=calendar.getTime();
                            boolean beforedateexp=newDate.before(current);

                            if(beforedateexp){

                                Calendar cal = Calendar.getInstance();
                                cal.set(myDate.getYear(),
                                        myDate.getMonth(),
                                        myDate.getDay(),
                                        15,
                                        11,
                                        00);

                                setAlarm(cal);
                                createNotificationChannel();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                    textView_btn_add_product.setVisibility(View.VISIBLE);
                       // textView_update_product.setVisibility(View.VISIBLE);
                        imageView_no_product.setVisibility(View.INVISIBLE);
                        textView_No_Product_Found.setVisibility(View.INVISIBLE);
                        textView_Add_product.setVisibility(View.INVISIBLE);
                    adapter = new ProductsAdapter(getContext(), mylist);
                    recyclerView.setAdapter(adapter);

                }else{
                        recyclerView.setVisibility(View.INVISIBLE);
                        textView_btn_add_product.setVisibility(View.INVISIBLE);
                       // textView_update_product.setVisibility(View.INVISIBLE);
                    imageView_no_product.setVisibility(View.VISIBLE);
                    textView_No_Product_Found.setVisibility(View.VISIBLE);
                    textView_Add_product.setVisibility(View.VISIBLE);

                }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                }

            });



    return root;
    }
    private void setAlarm(Calendar targetCal){
        Intent intent = new Intent(getContext(), MyAlaram.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        AlarmManager am = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "foxandroidReminderChannel";
            String description = "Channel For Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("foxandroid", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
    }