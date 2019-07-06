package com.revesion.restoorder.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.revesion.restoorder.R;

public class MainActivity extends AppCompatActivity {

    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.btnStart);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToHomeActivity();
            }
        });
    }

    private void sendUserToHomeActivity() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);

        finish();
    }
}
