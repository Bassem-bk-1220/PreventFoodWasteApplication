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

import com.example.projetlicence.Activity.MyOrdersSellerActivity;
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

public class ProfilSellerFragment extends Fragment {

    private CircleImageView photo_seller;
    private ImageView icon_edit;
    private TextView txtv_name_seller,txtv_email_seller,txtv_phone_seller,txtv_address_seller;
    private LinearLayout linearLayout_setting,linearLayout_help,linearLayout_logout,linearLayout_orders_recieved;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private String CurrentUserid;
    String name_seller="";
    String email_seller="";
    String phone_seller="";
    String address_seller="";
    String img_logo_seller="";
    FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View root = inflater.inflate(R.layout.fragment_profil_seller, container, false);
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
        linearLayout_orders_recieved=root.findViewById(R.id.linearL_orders_recieved);
        photo_seller=root.findViewById(R.id.logo_seller);
        firebaseAuth=FirebaseAuth.getInstance();
        icon_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SettingsSellerActivity.class);
                intent.putExtra("fullname",name_seller);
                intent.putExtra("email",email_seller);
                intent.putExtra("phone",phone_seller);
                intent.putExtra("address",address_seller);
                intent.putExtra("profileimage",img_logo_seller);
                startActivity(intent);
            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.child("profileimage").getValue() != null) {
                        img_logo_seller = snapshot.child("profileimage").getValue().toString();
                        Picasso.get().load(img_logo_seller).into(photo_seller);
                    }
                    if (snapshot.child("fullname").getValue() != null) {
                        name_seller = snapshot.child("fullname").getValue().toString();
                        txtv_name_seller.setText(name_seller);
                    }
                    if (snapshot.child("email").getValue() != null) {
                        email_seller = snapshot.child("email").getValue().toString();
                        txtv_email_seller.setText(email_seller);
                    }
                    if (snapshot.child("phone").getValue() != null) {
                        phone_seller = snapshot.child("phone").getValue().toString();
                        txtv_phone_seller.setText(phone_seller);
                    }
                    if (snapshot.child("address").getValue() != null) {
                        address_seller = snapshot.child("address").getValue().toString();
                        txtv_address_seller.setText(address_seller);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        linearLayout_orders_recieved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyOrdersSellerActivity.class);
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