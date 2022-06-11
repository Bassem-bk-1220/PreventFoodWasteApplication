package com.example.projetlicence.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.Activity.DetailsProductsClientActivity;
import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductsClientAdapter extends RecyclerView.Adapter<ProductsClientViewHolder>{
    private Context context;
    private List<Products> ListProduct;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser user;

    public ProductsClientAdapter(Context context, List<Products>product){
        this.context=context;
        this.ListProduct=product;
    }
    DatabaseReference myref;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @NonNull
    @NotNull
    @Override
    public ProductsClientViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_client,parent,false);
        return new ProductsClientViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductsClientViewHolder holder, int position) {
        Products products=ListProduct.get(position);
         mFirebaseAuth = FirebaseAuth.getInstance();
         user = mFirebaseAuth.getCurrentUser();
        database.getReference("favorites").child(user.getUid().toString()).child(products.getId_product()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    holder.imageView_faourite.setColorFilter(ContextCompat.getColor(context,R.color.red));

                }else{
                    holder.imageView_faourite.setColorFilter(ContextCompat.getColor(context,R.color.gris));
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        holder.textview_name_product.setText(products.getName_product());
        holder.textview_date_expiration.setText(products.getDate_expiration());
        holder.textView_quantity.setText(products.getQuantity());
        holder.textview_price.setText(products.getPrix());
        if(products.getUpdate()==true){
            holder.textview_price.setBackgroundColor(ContextCompat.getColor(context,R.color.red));
        }else{
            holder.textview_price.setBackgroundColor(ContextCompat.getColor(context,R.color.dark_green));
        }

        if(products.getProfileImage()!=null) {
            Picasso.get().load(products.getProfileImage()).into(holder.imageView_logo_product);
        }else{
            holder.imageView_logo_product.setImageResource(R.drawable.profile_picture);
        }
        holder.constraintLayout_products_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsProductsClientActivity.class);
                intent.putExtra("name_product",products.getName_product());
                intent.putExtra("ingredients",products.getIngredients());
                intent.putExtra("nutrition",products.getNutrition());
                intent.putExtra("profileImage",products.getProfileImage());
                intent.putExtra("id_product",products.getId_product());
                intent.putExtra("prix",products.getPrix());
                intent.putExtra("quantity",products.getQuantity());
                intent.putExtra("description_product",products.getDescription_product());
                context.startActivity(intent);
            }
        });
        holder.imageView_faourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database.getReference("favorites").child(user.getUid().toString()).child(products.getId_product()).setValue(products);


            }
        });

    }

    @Override
    public int getItemCount() {

        return ListProduct.size();
    }

}
