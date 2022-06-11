package com.example.projetlicence.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

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

public class ProductsFavoriteAdapter extends RecyclerView.Adapter<ProductsFavoriteViewHolder>{
    private Context context;
    private List<Products> ListProduct;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser user;

    public ProductsFavoriteAdapter(Context context, List<Products>product){
        this.context=context;
        this.ListProduct=product;
    }
    DatabaseReference myref;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @NonNull
    @NotNull
    @Override
    public ProductsFavoriteViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_client,parent,false);
        return new ProductsFavoriteViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductsFavoriteViewHolder holder, int position) {
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
        holder.textview_price.setText(products.getPrix());

        if(products.getProfileImage()!=null) {
            Picasso.get().load(products.getProfileImage()).into(holder.imageView_logo_product);
        }else{
            holder.imageView_logo_product.setImageResource(R.drawable.profile_picture);
        }

    }

    @Override
    public int getItemCount() {

        return ListProduct.size();
    }

}
