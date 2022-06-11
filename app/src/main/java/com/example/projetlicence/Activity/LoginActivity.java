package com.example.projetlicence.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText edittxt_email, edittxt_password;
    TextView txtview_forget_password, txtview_login, txtview_sign_up;
    FirebaseAuth mFirebaseAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edittxt_email = findViewById(R.id.et_Email_log);
        edittxt_password = findViewById(R.id.et_Password_log);
        txtview_forget_password = findViewById(R.id.txt_forget_password);
        txtview_login = findViewById(R.id.txt_Sign_in);
        txtview_sign_up=findViewById(R.id.txt_sign_up);
        mFirebaseAuth = FirebaseAuth.getInstance();
        txtview_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edittxt_email.getText().toString();
                final String password = edittxt_password.getText().toString();
                String response =  validate_sign(username, password);
                if (response.equals("please provide your email")) {
                    edittxt_email.setError("please provide your username");
                    edittxt_email.requestFocus();
                } else if (response.equals("please provide your password")) {
                    edittxt_password.setError("please provide your password");
                    edittxt_password.requestFocus();
                } else if (response.equals("the email and password correct")) {
                    mFirebaseAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        database.getReference("users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                // This method is called once with the initial value and again
                                                // whenever data at this location is updated.
                                                Users user = dataSnapshot.getValue(Users.class);
                                                SharedPreferences sharedPreferences=getSharedPreferences("infoUser",MODE_PRIVATE);
                                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                                editor.putString("email",user.getEmail());
                                                editor.putString("fullname",user.getFullname());
                                                editor.putString("phone",user.getPhone());
                                                editor.putString("profileimage",user.getProfileimage());
                                                editor.commit();
                                                if(user.occupation.equals("Seller")) {
                                                    Intent intent = new Intent(getApplicationContext(), MenuSellerActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }else if(user.occupation.equals("Client")) {
                                                    Intent intent = new Intent(getApplicationContext(), MenuClientActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError error) {
                                                // Failed to read value

                                            }
                                        });
                                        Toast.makeText(LoginActivity.this, "Authentication successufull.",
                                                Toast.LENGTH_SHORT).show();


                                    } else {

                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        txtview_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txtview_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public String validate_sign(String email, String password) {

        if (email.isEmpty()) {
            return "please provide your email";
        } else if (password.isEmpty()) {
            return "please provide your password";
        } else {
            return "the email and password correct";
        }
    }
}