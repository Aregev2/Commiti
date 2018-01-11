package com.aregev2.commiti;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;

    ActionBarDrawerToggle actionBarDrawerToggle;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.activity_main_drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.activity_main_navigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.activity_main_menu_drawer_volunteer:
                Toast.makeText(this, "V", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.activity_main_menu_drawer_reports:
                Toast.makeText(this, "R", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.activity_main_menu_drawer_settings:
                Toast.makeText(this, "S", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.activity_main_menu_drawer_profile:
                Toast.makeText(this, "P", Toast.LENGTH_SHORT).show();
                return true;
        }

        return false;
    }
}
