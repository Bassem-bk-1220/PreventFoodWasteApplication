package com.example.projetlicence.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.example.projetlicence.Fragment.FavoriteFragment;
import com.example.projetlicence.Fragment.ListProductFragment;
import com.example.projetlicence.Fragment.MessageClientFragment;
import com.example.projetlicence.Fragment.ProfilClientFragment;
import com.example.projetlicence.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MenuClientActivity extends AppCompatActivity {

    private static final String TAG = MenuSellerActivity.class.getSimpleName();
    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_client);
        bottomNav=findViewById(R.id.bottom_nav_client);
        if(savedInstanceState == null){
            bottomNav.setItemSelected(R.id.list,true);
            fragmentManager=getSupportFragmentManager();
            ListProductFragment listProductFragment=new ListProductFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,listProductFragment).commit();
        }
        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment=null;
                switch (id){
                    case R.id.list:
                        fragment=new ListProductFragment();
                        break;
                    case R.id.favorite:
                        fragment=new FavoriteFragment();
                        break;
                    case R.id.message:
                        fragment=new MessageClientFragment();
                        break;
                    case R.id.profil:
                        fragment=new ProfilClientFragment();
                        break;
                }
                if(fragment!= null){
                    fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                }else{
                    Log.e(TAG, "Error in creating fragment: ");
                }
            }
        });
    }
}