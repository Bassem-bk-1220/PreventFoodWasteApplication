package com.example.projetlicence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetlicence.Modele.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    EditText edtxt_fullName,edtxt_occupation,edtxt_phone,edtxt_email,edtxt_password;
    TextView tv_Sign_up,tv_sign_in;
    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase firebaseDatabase;
    int maxid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtxt_fullName=findViewById(R.id.et_Fullname);
        edtxt_occupation=findViewById(R.id.et_occupation);
        edtxt_phone=findViewById(R.id.et_phone);
        edtxt_email=findViewById(R.id.et_Email);
        edtxt_password=findViewById(R.id.et_Password);

        tv_Sign_up=findViewById(R.id.et_Sign_up);
        tv_sign_in=findViewById(R.id.txt_sign_in);

        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        tv_Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname = edtxt_fullName.getText().toString().trim();
                final String occupation = edtxt_occupation.getText().toString().trim();
                final String email = edtxt_email.getText().toString().trim();
                final String phone = edtxt_phone.getText().toString().trim();
                final String password = edtxt_password.getText().toString().trim();

                if (fullname.isEmpty()) {
                    edtxt_fullName.setError("please provide your fullname");
                    edtxt_fullName.requestFocus();
                } else if (occupation.isEmpty()) {
                    edtxt_occupation.setError("please provide your occupation");
                    edtxt_occupation.requestFocus();
                }else if (phone.isEmpty()) {
                    edtxt_phone.setError("please provide your phone");
                    edtxt_phone.requestFocus();
                }  else if (email.isEmpty()) {
                    edtxt_email.setError("Email is required");
                    edtxt_email.requestFocus();
                } else if (password.isEmpty()) {
                    edtxt_password.setError("please provide your password");
                    edtxt_password.requestFocus();
                } else if (password.length() <6) {
                    edtxt_password.setError("Min password  length should 6 characters!");
                    edtxt_password.requestFocus();
                }  else if (!(email.isEmpty() && password.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Users user=new Users(fullname,occupation,phone,email,password);
                                        // Write a message to the database

                                        Task<Void> myRef = FirebaseDatabase.getInstance().getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {

                                                            Toast.makeText(RegisterActivity.this, "user has been registered successuful!",
                                                                    Toast.LENGTH_LONG).show();

                                                        }else {
                                                            Toast.makeText(RegisterActivity.this, "failed to register, try again!",
                                                                    Toast.LENGTH_LONG).show();

                                                        }
                                                    }
                                                });
                                        FirebaseDatabase.getInstance().getReference("Users").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.exists()){
                                                    maxid= (int) snapshot.getChildrenCount();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }


        });
        tv_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}