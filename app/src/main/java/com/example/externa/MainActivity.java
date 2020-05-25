package com.example.externa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView registro;
    EditText usuario, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registro = (TextView)findViewById(R.id.textView);
        usuario = (EditText) findViewById(R.id.editText2);
        pass = (EditText) findViewById(R.id.editText3);
        this.setTitle(R.string.act_1);

    }
    public void registro (View v){
        Intent registrar = new Intent(this,Registro.class);
        startActivity(registrar);
        finish();
    }
    public void ingresar (View v){
        final String Correocli = usuario.getText().toString();
        final String Clavecli = pass.getText().toString();
        Response.Listener<String> responListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success)
                    {
                        usuario.setText("");
                        pass.setText("");
                        Intent pasar = new Intent(MainActivity.this, Entry.class);
                        startActivity(pasar);
                    }
                    else
                    {
                        AlertDialog.Builder hh = new AlertDialog.Builder(MainActivity.this);
                        hh.setMessage("Correo o contraseña incorrecta")
                                .setNegativeButton("Reintentar",null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        LoginRequest datos= new LoginRequest (Correocli,Clavecli,responListener);
        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        queue.add(datos);
    }
}

