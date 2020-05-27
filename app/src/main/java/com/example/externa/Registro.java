package com.example.externa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity {
    EditText nombre1,apellido1,edad1,numtelefono1,correo1,clave1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre1 = (EditText)findViewById(R.id.nombre);
        apellido1 = (EditText)findViewById(R.id.apellido);
        edad1 = (EditText)findViewById(R.id.edad);
        numtelefono1 = (EditText)findViewById(R.id.numTelefono);
        correo1 = (EditText)findViewById(R.id.correo);
        clave1 = (EditText)findViewById(R.id.clave);
        this.setTitle(R.string.act_2);
    }
    public void datos(View view) {
        final String Nomcli = nombre1.getText().toString();
        final String Apecli = apellido1.getText().toString();
        final Integer Edadcli = Integer.parseInt(edad1.getText().toString());
        final String Correocli = correo1.getText().toString();
        final Integer Telecli = Integer.parseInt(numtelefono1.getText().toString());
        final String Clavecli = clave1.getText().toString();

        Log.d("REGISTRO", "Entro metodo");

        if (!Nomcli.equals("") && !Apecli.equals("") && !Edadcli.equals("") && !Correocli.equals("") && !Telecli.equals("") && !Clavecli.equals("")) {

            Response.Listener<String> responListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            Toast.makeText(getApplicationContext(), "Registrado",Toast.LENGTH_LONG).show();
                            Intent pasar = new Intent(Registro.this, MainActivity.class);
                            startActivity(pasar);
                        } else {
                            AlertDialog.Builder hh = new AlertDialog.Builder(Registro.this);
                            hh.setMessage("No se registro")
                                    .setNegativeButton("Retry", null)
                                    .create().show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
            };

            Response.ErrorListener error = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ERROR REGISTRO", error.toString());
                }
            };

            Servidorweb1 datos = new Servidorweb1(Nomcli, Apecli, Edadcli, Correocli, Telecli, Clavecli, responListener, error);
            RequestQueue queue = Volley.newRequestQueue(Registro.this);
            queue.add(datos);
        }
        else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
}
