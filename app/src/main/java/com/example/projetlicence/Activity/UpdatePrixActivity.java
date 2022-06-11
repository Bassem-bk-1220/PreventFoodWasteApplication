package com.example.projetlicence.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdatePrixActivity extends AppCompatActivity {
EditText editText_prix, editText_quantity;
TextView textView_update_data;
DatabaseReference databaseReference;
FirebaseDatabase firebaseDatabase;
Products products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_prix);
        editText_prix=findViewById(R.id.edtxt_prix);
        editText_quantity=findViewById(R.id.edtxt_quantity);
        textView_update_data=findViewById(R.id.txtv_update_data);
        firebaseDatabase= FirebaseDatabase.getInstance();
        String prix=getIntent().getStringExtra("prix");
        String quantity=getIntent().getStringExtra("quantity");
        String id_prod=getIntent().getStringExtra("id_product");
        editText_prix.setText(prix);
        editText_quantity.setText(quantity);
        databaseReference = firebaseDatabase.getReference().child("products").child(id_prod);

        textView_update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.child("prix").setValue(editText_prix.getText().toString());
                databaseReference.child("quantity").setValue(editText_quantity.getText().toString());
                databaseReference.child("update").setValue(true);
                Toast.makeText(UpdatePrixActivity.this, "Update completed", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }
}