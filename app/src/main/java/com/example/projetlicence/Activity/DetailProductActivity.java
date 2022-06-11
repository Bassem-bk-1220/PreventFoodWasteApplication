package com.example.projetlicence.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetlicence.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class DetailProductActivity extends AppCompatActivity {
    TextView  textView_name_product,textView_description_product,textView_description_ingredients,
            textView_description_Nutrition;
    ImageView imageView_product;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private String CurrentUserid;
    String logo_product="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        textView_name_product=findViewById(R.id.tv_name_product);
        textView_description_product=findViewById(R.id.tv_description_product);
        textView_description_ingredients=findViewById(R.id.tv_description_ingredients);
        textView_description_Nutrition=findViewById(R.id.tv_description_Nutrition);
        imageView_product=findViewById(R.id.image_product);
        String name= getIntent().getStringExtra("name_product");
        textView_name_product.setText(name);
        String description_product=getIntent().getStringExtra("description_product");
        textView_description_product.setText(description_product);
        String ingredients=getIntent().getStringExtra("ingredients");
        textView_description_ingredients.setText(ingredients);
        String nutrition=getIntent().getStringExtra("nutrition");
        textView_description_Nutrition.setText(nutrition);
        logo_product=getIntent().getStringExtra("profileImage");
        Picasso.get().load(logo_product).into(imageView_product);

    }
}