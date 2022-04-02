package com.example.projetlicence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

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
                    case R.id.profil:
                        fragment=new ProfilFragment();
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