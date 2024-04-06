package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button irButton = findViewById(R.id.ir);
        Button regresarButton = findViewById(R.id.regresar2);

        irButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a MainActivity2
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });

        regresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Regresar a MainActivity
                finish();
            }
        });



    }
}
