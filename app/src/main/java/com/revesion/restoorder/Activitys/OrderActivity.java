package com.revesion.restoorder.Activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.revesion.restoorder.Entities.Menu;
import com.revesion.restoorder.Fragments.ItemFragment;
import com.revesion.restoorder.R;

import java.util.Objects;

public class OrderActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    TextView montantTotale;
    Button terminer, annuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        Objects.requireNonNull(getSupportActionBar()).setTitle("Order " + getIntent().getStringExtra("nomRes"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setDisplayShowCustomEnabled(true);

        ItemFragment fi = new ItemFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_order, fi);

        ft.commit();


        terminer = findViewById(R.id.terminer);
        terminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        annuler = findViewById(R.id.cancel);
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public void onListFragmentInteraction(Menu item) {

    }
}
