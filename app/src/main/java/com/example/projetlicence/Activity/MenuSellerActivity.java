package com.example.projetlicence.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.example.projetlicence.Fragment.HomeFragment;
import com.example.projetlicence.Fragment.MessageClientFragment;
import com.example.projetlicence.Fragment.NotificationFragment;
import com.example.projetlicence.Fragment.ProfilSellerFragment;
import com.example.projetlicence.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MenuSellerActivity extends AppCompatActivity {
    private static final String TAG = MenuSellerActivity.class.getSimpleName();
    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_seller);
        bottomNav=findViewById(R.id.bottom_nav);
        if(savedInstanceState == null){
            bottomNav.setItemSelected(R.id.home,true);
            fragmentManager=getSupportFragmentManager();
            HomeFragment homeFragment=new HomeFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,homeFragment).commit();
        }
        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment=null;
                switch (id){
                    case R.id.home:
                        fragment=new HomeFragment();
                        break;
                    case R.id.notification:
                        fragment=new NotificationFragment();
                        break;
                    case R.id.messenger:
                        fragment=new MessageClientFragment();
                        break;
                    case R.id.profil:
                        fragment=new ProfilSellerFragment();
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