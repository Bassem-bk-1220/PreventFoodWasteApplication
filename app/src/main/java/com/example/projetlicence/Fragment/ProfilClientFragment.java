package com.example.projetlicence.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projetlicence.R;
import com.example.projetlicence.Activity.HelpActivity;
import com.example.projetlicence.Activity.LoginActivity;
import com.example.projetlicence.Activity.MyOrdersClientActivity;
import com.example.projetlicence.Activity.SettingsSellerActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfilClientFragment extends Fragment {

    private CircleImageView photo_client;
    private ImageView icon_edit;
    private TextView txtv_name_seller,txtv_email_seller,txtv_phone_seller,txtv_address_seller;
    private LinearLayout linearLayout_setting,linearLayout_help,linearLayout_logout,linearLayout_list,linearLayout_notification;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private String CurrentUserid;
    String name_client="";
    String email_client="";
    String phone_client="";
    String address_client="";
    String img_logo_client="";
    FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profil_client, container, false);
        mAuth=FirebaseAuth.getInstance();
        CurrentUserid=mAuth.getCurrentUser().getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("users").child(CurrentUserid);
        txtv_name_seller=root.findViewById(R.id.tv_name_seller);
        txtv_email_seller=root.findViewById(R.id.tv_email_seller);
        txtv_phone_seller=root.findViewById(R.id.tv_phone_seller);
        txtv_address_seller=root.findViewById(R.id.tv_address_seller);
        icon_edit=root.findViewById(R.id.imgv_edit);
        linearLayout_setting =root.findViewById(R.id.linearL_setting);
        linearLayout_help =root.findViewById(R.id.linearL_help);
        linearLayout_logout =root.findViewById(R.id.linearL_logout);
        linearLayout_list=root.findViewById(R.id.linearL_list);

        photo_client=root.findViewById(R.id.logo_client);
        firebaseAuth=FirebaseAuth.getInstance();
        icon_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SettingsSellerActivity.class);
                intent.putExtra("fullname",name_client);
                intent.putExtra("email",email_client);
                intent.putExtra("phone",phone_client);
                intent.putExtra("address",address_client);
                intent.putExtra("profileimage",img_logo_client);
                startActivity(intent);
            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.child("profileimage").getValue() != null) {
                        img_logo_client = snapshot.child("profileimage").getValue().toString();
                        Picasso.get().load(img_logo_client).into(photo_client);
                    }
                    if (snapshot.child("fullname").getValue() != null) {
                        name_client = snapshot.child("fullname").getValue().toString();
                        txtv_name_seller.setText(name_client);
                    }
                    if (snapshot.child("email").getValue() != null) {
                        email_client = snapshot.child("email").getValue().toString();
                        txtv_email_seller.setText(email_client);
                    }
                    if (snapshot.child("phone").getValue() != null) {
                        phone_client = snapshot.child("phone").getValue().toString();
                        txtv_phone_seller.setText(phone_client);
                    }
                    if (snapshot.child("address").getValue() != null) {
                        address_client = snapshot.child("address").getValue().toString();
                        txtv_address_seller.setText(address_client);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        linearLayout_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyOrdersClientActivity.class);
                startActivity(intent);
            }
        });
        linearLayout_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SettingsSellerActivity.class);
                startActivity(intent);
            }
        });
        linearLayout_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });
        linearLayout_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intent=new Intent(getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return root;
    }
}