package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btnBackToMain = findViewById(R.id.regresar2);
        Button btnSaveData = findViewById(R.id.buttonguardarDatos);

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Regresar a MainActivity
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
                finish();
            }
        });

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Agrega un log para verificar que se está ejecutando el onClick
                Log.d("MainActivity2", "Botón 'Guardar Datos' clickeado");

                // Obtener los datos ingresados en los EditText
                EditText editTextId = findViewById(R.id.editTextId);
                EditText editTextUsuario = findViewById(R.id.editTextUsuario);
                String id = editTextId.getText().toString();
                String usuario = editTextUsuario.getText().toString();

                // Realizar la solicitud HTTP POST para guardar los datos en la base de datos
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("http://192.168.0.13/claseandroid/api.php");
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setDoOutput(true);

                            // Construir los datos a enviar
                            String data = "&id=" + URLEncoder.encode(id, "UTF-8") + "&nombre=" + URLEncoder.encode(usuario, "UTF-8");

                            // Escribir los datos en el cuerpo de la solicitud
                            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                            writer.write(data);
                            writer.flush();

                            // Obtener la respuesta del servidor
                            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                            StringBuilder response = new StringBuilder();
                            String line;
                            while ((line = reader.readLine()) != null) {
                                response.append(line);
                            }

                            // Mostrar la respuesta del servidor en los logs
                            Log.d("MainActivity2", "Respuesta del servidor: " + response.toString());

                            // Cerrar conexiones
                            writer.close();
                            reader.close();
                            conn.disconnect();
                        } catch (Exception e) {
                            // Manejar errores
                            e.printStackTrace();
                            Log.e("MainActivity2", "Error al realizar la solicitud HTTP POST: " + e.getMessage());
                        }
                    }
                }).start();
            }
        });
    }
}
