package com.example.projetlicence.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetlicence.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsSellerActivity extends AppCompatActivity {
    private EditText edtxt_Email_seller,edtxt_phone_seller,edtxt_Address_seller;
    private TextView txtv_update;
    private CircleImageView photo_seller;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private static final int Gallery_Pick=1;
    private StorageReference storageReference;
    private ProgressDialog loadingbar;
    String CurrentUserID;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_seller);
        edtxt_Email_seller=findViewById(R.id.editt_Email_seller);
        edtxt_phone_seller=findViewById(R.id.editt_phone_seller);
        edtxt_Address_seller=findViewById(R.id.editt_Address_seller);
        photo_seller=findViewById(R.id.update_logo_seller);
        txtv_update=findViewById(R.id.txt_update);

        String email_seller=getIntent().getStringExtra("email");
        String phone_seller=getIntent().getStringExtra("phone");
        String address_seller=getIntent().getStringExtra("address");
        String img_logo_seller=getIntent().getStringExtra("profileimage");

        if(img_logo_seller != null && !img_logo_seller.isEmpty()) {
            Picasso.get().load(img_logo_seller).into(photo_seller);
        }
        edtxt_Email_seller.setText(email_seller);
        edtxt_phone_seller.setText(phone_seller);
        edtxt_Address_seller.setText(address_seller);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        loadingbar = new ProgressDialog(this);
        CurrentUserID = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("users");
        storageReference = FirebaseStorage.getInstance().getReference().child("image seller");
        photo_seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, Gallery_Pick);
            }
        });
        txtv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateData();
            }
        });
    }

    private void ValidateData() {
        final String email = edtxt_Email_seller.getText().toString();
        final String phone = edtxt_phone_seller.getText().toString();
        final String address = edtxt_Address_seller.getText().toString();

        boolean response=checkEmailForValidity(email);
        if (response==false) {
            Toast.makeText(SettingsSellerActivity.this, "Please write your email...", Toast.LENGTH_LONG).show();
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(SettingsSellerActivity.this, "Please write your phone...", Toast.LENGTH_LONG).show();
        }
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(SettingsSellerActivity.this, "Please write your address...", Toast.LENGTH_LONG).show();
        }
         if (imageUri == null) {
            Toast.makeText(SettingsSellerActivity.this, "Please select an image", Toast.LENGTH_LONG).show();
        }else{
            loadingbar.setTitle("adding setup  profile");
            loadingbar.setMessage("the data are updating to firebase....");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();
            if(imageUri!=null) {
                storageReference.child(CurrentUserID).putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        storageReference.child(CurrentUserID).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                HashMap hashMap = new HashMap();
                                hashMap.put("email", email);
                                hashMap.put("address", address);
                                hashMap.put("phone", phone);
                                hashMap.put("profileimage", uri.toString());
                                databaseReference.child(CurrentUserID).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        finish();
                                        loadingbar.dismiss();
                                        Toast.makeText(SettingsSellerActivity.this, "Setup profile completed", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        loadingbar.dismiss();
                                        Toast.makeText(SettingsSellerActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }else{
                HashMap hashMap = new HashMap();
                hashMap.put("email", email);
                hashMap.put("address", address);
                hashMap.put("phone", phone);
                databaseReference.child(CurrentUserID).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        finish();
                        loadingbar.dismiss();
                        Toast.makeText(SettingsSellerActivity.this, "Setup profile completed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        loadingbar.dismiss();
                        Toast.makeText(SettingsSellerActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
    public static boolean checkEmailForValidity(String email) {

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();

    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Gallery_Pick && resultCode==RESULT_OK && data!=null) {
            imageUri=data.getData();
            photo_seller.setImageURI(imageUri);
        }
    }
}