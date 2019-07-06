package com.revesion.restoorder.Activitys;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.revesion.restoorder.Entities.Menu;
import com.revesion.restoorder.Entities.Resto;
import com.revesion.restoorder.Fragments.MenuFragment;
import com.revesion.restoorder.Fragments.RestoFragment;
import com.revesion.restoorder.R;

public class HomeActivity extends AppCompatActivity implements RestoFragment.OnListFragmentInteractionListener, MenuFragment.OnListFragmentInteractionListener {

    SQLiteDatabase liteDB;

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        liteDB = openOrCreateDatabase("orderDb", MODE_PRIVATE, null);

        liteDB.execSQL("CREATE TABLE IF NOT EXISTS commands(id INTEGER PRIMARY KEY,nom TEXT,price TEXT);");

        RestoFragment rf = new RestoFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, rf);
        ft.addToBackStack(null);
        ft.commit();

        title = findViewById(R.id.title_home);

    }


    @Override
    public void onListFragmentInteraction(Resto item) {

    }

    @Override
    public void onListFragmentInteraction(Menu item) {

    }
}
