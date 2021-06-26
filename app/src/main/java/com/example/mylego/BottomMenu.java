package com.example.mylego;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.mylego.databinding.ActivityBottomMenuBinding;

public class BottomMenu extends AppCompatActivity {

    private ActivityBottomMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialise database and come back here
        //only once if database not exist
        if(!dbTableExists("brick_set")) {
            int INIT_DATABASE = 1;
            Intent i = new Intent(this, MainActivity.class);
            startActivityForResult(i, INIT_DATABASE);
        }


        binding = ActivityBottomMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_sets, R.id.navigation_parts, R.id.navigation_my_collection, R.id.navigation_moc)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_bottom_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }

    public boolean dbTableExists(String tableName) {
        SQLiteDatabase mDatabase = openOrCreateDatabase("BricksSet.db", Context.MODE_PRIVATE,null);

        Cursor c = null;
        boolean tableExists = false;
        /* get cursor on it */
        try
        {
            c = mDatabase.query(tableName, null,
                    null, null, null, null, null);
            tableExists = true;
        }
        catch (Exception e) {
            /* fail */
            Log.d("Database state", "BricksSet.db database doesn't exist");
        }

        return tableExists;
    }

}