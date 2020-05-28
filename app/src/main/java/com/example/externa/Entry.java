package com.example.externa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;

public class Entry extends AppCompatActivity {

    EditText tipo_doc1,cedula1,genero1,estado1,ciudad_residencia1,dir1,ciudad_nacimiento1,telefono_fijo1;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        this.setTitle(R.string.act_4);
        tipo_doc1 = (EditText)findViewById(R.id.edit2);
        cedula1 = (EditText)findViewById(R.id.edit3);
        genero1 = (EditText)findViewById(R.id.edit5);
        estado1 = (EditText)findViewById(R.id.edit6);
        ciudad_residencia1 = (EditText)findViewById(R.id.edit7);
        dir1 = (EditText)findViewById(R.id.edit8);
        ciudad_nacimiento1 = (EditText)findViewById(R.id.edit9);
        telefono_fijo1 = (EditText)findViewById(R.id.edit10);
        b = getIntent().getExtras();

    }


    public void guardar(View view) {

        final String tipo_doc = tipo_doc1.getText().toString();
        final String cedula2 = cedula1.getText().toString();
        final String genero = genero1.getText().toString();
        final String estado = estado1.getText().toString();
        final String ciudad_residencia = ciudad_residencia1.getText().toString();
        final String dir = dir1.getText().toString();
        final String ciudad_nacimiento = ciudad_nacimiento1.getText().toString();
        final String telefono_fijo2 = telefono_fijo1.getText().toString();
        final Integer id_cliente = Integer.parseInt(b.getString("usuario"));
        if(tipo_doc.isEmpty() || cedula2.isEmpty() || genero.isEmpty()  || estado.isEmpty()  || ciudad_residencia.isEmpty() || dir.isEmpty() || ciudad_nacimiento.isEmpty() || telefono_fijo2.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Llenar todos los campos",Toast.LENGTH_LONG).show();
            return;
        }
        final Integer cedula = Integer.parseInt(cedula1.getText().toString());
        final Integer telefono_fijo = Integer.parseInt(telefono_fijo1.getText().toString());
        Log.d("REGISTRO", "Entro metodo");

            Response.Listener<String> responListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String id_cliente1 = b.getString("usuario");
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            Bundle d = new Bundle();
                            d.putString("usuario",id_cliente1);
                            Toast.makeText(getApplicationContext(), "Info guardada",Toast.LENGTH_LONG).show();
                            Intent pasar = new Intent(Entry.this, Bienvenidos.class);
                            pasar.putExtras(d);
                            startActivity(pasar);
                        } else {
                            AlertDialog.Builder hh = new AlertDialog.Builder(Entry.this);
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

            Personal datos = new Personal(id_cliente,tipo_doc,cedula,genero,estado,ciudad_residencia,dir,ciudad_nacimiento,telefono_fijo, responListener, error);
            RequestQueue queue = Volley.newRequestQueue(Entry.this);
            queue.add(datos);
        }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem cerrar){
        int id = cerrar.getItemId();
        if(id==R.id.cerrar){
            Toast.makeText(this, "Cerrar sesion", Toast.LENGTH_LONG).show();
            finish();
            Intent ii = new Intent(this,MainActivity.class);
            startActivity(ii);
        }
        return super.onOptionsItemSelected(cerrar);
    }

}

