package com.example.projetlicence.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DetailsProductsClientActivity extends AppCompatActivity {
    TextView textView_name_product,textView_description_product,textView_description_ingredients,
            textView_description_Nutrition,textView_add_cart,textView_quantity,textView_moins,textView_plus;
    ImageView imageView_product;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private String CurrentUserid;
    String logo_product="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_products_client);
        textView_name_product=findViewById(R.id.tv_name_product);
        textView_description_product=findViewById(R.id.tv_description_product);
        textView_description_ingredients=findViewById(R.id.tv_description_ingredients);
        textView_description_Nutrition=findViewById(R.id.tv_description_Nutrition);
        textView_moins=findViewById(R.id.txtv_moins);
        textView_plus=findViewById(R.id.txtv_plus);
        textView_quantity=findViewById(R.id.txtv_quantity);
        textView_add_cart=findViewById(R.id.txtv_add_cart);
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
        FirebaseUser user;
        FirebaseAuth mFirebaseAuth;
        mFirebaseAuth = FirebaseAuth.getInstance();
        user = mFirebaseAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String id_product= getIntent().getStringExtra("id_product");
        String prix= getIntent().getStringExtra("prix");
        String quantity= getIntent().getStringExtra("quantity");


        textView_add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Products product=new Products(name,prix,logo_product,quantity,id_product);

                database.getReference("cart").child(user.getUid().toString()).child(id_product).setValue(product);
                finish();
            }
        });
    }
}