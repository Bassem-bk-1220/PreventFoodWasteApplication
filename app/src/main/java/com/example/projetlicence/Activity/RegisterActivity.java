package com.example.projetlicence.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetlicence.Modele.Users;
import com.example.projetlicence.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText edtxt_fullName,edtxt_occupation,edtxt_phone,edtxt_email,edtxt_password;
    TextView tv_Sign_up,tv_sign_in;
     String fullname,occupation,email,phone,password;
    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabase;
    private static final String USERS = "users";
    private String TAG = "RegisterActivity";
    private Users user;
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
        mDatabase = firebaseDatabase.getReference(USERS);

        tv_Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 fullname = edtxt_fullName.getText().toString().trim();
                 occupation = edtxt_occupation.getText().toString().trim();
                 email = edtxt_email.getText().toString().trim();
                 phone = edtxt_phone.getText().toString().trim();
                 password = edtxt_password.getText().toString().trim();

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
                       user=new Users(fullname,occupation,phone,email,password);
                    registerUser();


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

        public void registerUser() {
            mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser userFirebase = mFirebaseAuth.getCurrentUser();
                                user.setId_user(userFirebase.getUid());
                                updateUI(userFirebase);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

        /**
         * adding user information to database and redirect to login screen
         * @param currentUser
         */
        public void updateUI(FirebaseUser currentUser) {

            mDatabase.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user); //adding user info to database
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }



}