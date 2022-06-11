package com.example.projetlicence.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.Activity.DetailProductActivity;
import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.R;
import com.example.projetlicence.Activity.UpdatePrixActivity;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductsAdapter  extends RecyclerView.Adapter<ProductsViewHolder>{
    private Context context;
    private List<Products> ListProduct;
    public ProductsAdapter(Context context,List<Products>product){
        this.context=context;
        this.ListProduct=product;
    }
    @NonNull
    @NotNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductsViewHolder holder, int position) {
        Products products=ListProduct.get(position);
        holder.textview_name_product.setText(products.getName_product());
        holder.textview_date_expiration.setText(products.getDate_expiration());
        holder.textview_price.setText(products.getPrix());

        holder.textView_quantity.setText(products.getQuantity());
        holder.imageView_update_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, UpdatePrixActivity.class);
                intent.putExtra("prix",products.getPrix());
                intent.putExtra("quantity",products.getQuantity());
                intent.putExtra("id_product",products.getId_product());
                context.startActivity(intent);
            }
        });

        if(products.getProfileImage()!=null) {
            Picasso.get().load(products.getProfileImage()).into(holder.imageView_logo_product);
        }else{
            holder.imageView_logo_product.setImageResource(R.drawable.profile_picture);
        }
        holder.textview_name_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailProductActivity.class);
                intent.putExtra("name_product",products.getName_product());
                intent.putExtra("ingredients",products.getIngredients());
                intent.putExtra("nutrition",products.getNutrition());
                intent.putExtra("profileImage",products.getProfileImage());
                intent.putExtra("description_product",products.getDescription_product());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return ListProduct.size();
    }

}
