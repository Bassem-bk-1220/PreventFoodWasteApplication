package com.example.projetlicence.Activity;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

public class AddProductActivity extends AppCompatActivity  {
    EditText editxt_name_product,editxt_ingredients,editxt_Nutrition,editxt_date_expiration,editxt_description,
            editxt_prix,editxt_quantity;
TextView txtview_save;
ImageView imagView_product,imgview_date_expiration;
    private static final int Gallery_Pick=1;
    private StorageReference storageReference;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private ProgressDialog loadingbar;
    String CurrentUserID;
    Uri imageUri;
    private int requestCode;
    private int resultCode;
    Products products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        imagView_product= findViewById(R.id.imagV_product);
        editxt_name_product=findViewById(R.id.edtxt_name_product);
        editxt_ingredients=findViewById(R.id.edtxt_ingredients);
        editxt_Nutrition=findViewById(R.id.edtxt_Nutrition);
        editxt_date_expiration=findViewById(R.id.edtxt_date_expiration);
        editxt_description=findViewById(R.id.edtxt_description_prod);
        editxt_prix=findViewById(R.id.edtxt_prix);
        editxt_quantity=findViewById(R.id.edtxt_quantity);
        txtview_save = findViewById(R.id.txtv_save);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        loadingbar = new ProgressDialog(this);
        CurrentUserID = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("products");
        storageReference = FirebaseStorage.getInstance().getReference().child("imageProducts");

        String nutrition=getIntent().getStringExtra("nutrition");
        editxt_Nutrition.setText(nutrition);
        String ingredient=getIntent().getStringExtra("ingredient");
        editxt_ingredients.setText(ingredient);

        imagView_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent=new Intent(AddProductActivity.this,TestActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, Gallery_Pick);
            }
        });
        txtview_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });
    }

    private void SaveData() {
        final String name_product = editxt_name_product.getText().toString();
        final String ingredients = editxt_ingredients.getText().toString();
        final String nutrition = editxt_Nutrition.getText().toString();
        final String date_expiration = editxt_date_expiration.getText().toString();
        final String description_product = editxt_description.getText().toString();
        final String prix = editxt_prix.getText().toString();
        final  String quantity=editxt_quantity.getText().toString();
        loadingbar.setTitle("adding setup  profile");
        loadingbar.setMessage("the data are updating to firebase....");
        loadingbar.setCanceledOnTouchOutside(false);
        loadingbar.show();
        String key=databaseReference.push().getKey();
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser seller = mFirebaseAuth.getCurrentUser();

        if(imageUri!=null) {
            storageReference.child(key).putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<UploadTask.TaskSnapshot> task) {
                    storageReference.child(key).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                         products=new Products(name_product,ingredients,nutrition,date_expiration,description_product,prix,uri.toString(),quantity,key,seller.getUid());

                            databaseReference.child(key).setValue(products).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    finish();
                                    loadingbar.dismiss();
                                    Toast.makeText(AddProductActivity.this, "Setup to  add the product completed", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    loadingbar.dismiss();
                                    Toast.makeText(AddProductActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                 });
               }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Gallery_Pick && resultCode==RESULT_OK && data!=null) {
            imageUri=data.getData();
            imagView_product.setImageURI(imageUri);
        }
     }


}