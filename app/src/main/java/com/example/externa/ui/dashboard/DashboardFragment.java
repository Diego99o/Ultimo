package com.example.externa.ui.dashboard;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.externa.Bienvenidos;
import com.example.externa.ConsultaDatosPersonales;
import com.example.externa.MainActivity;
import com.example.externa.R;

import org.json.JSONException;
import org.json.JSONObject;

public class DashboardFragment extends Fragment {
    TextView nombre1,apellido1,edad1,celular1,clasedoc1,numdoc1,genero1,ciudad_r,direcion_r,ciudad_n,num_fijo;
    Bundle bb;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        nombre1 = root.findViewById(R.id.Text3);
        apellido1 = root.findViewById(R.id.Text5);
        edad1 =root.findViewById(R.id.Text7);
        celular1 =root.findViewById(R.id.Text9);
        clasedoc1 =root.findViewById(R.id.Text11);
        numdoc1 =root.findViewById(R.id.Text13);
        genero1 =root.findViewById(R.id.Text15);
        ciudad_r =root.findViewById(R.id.Text17);
        direcion_r =root.findViewById(R.id.Text19);
        ciudad_n =root.findViewById(R.id.Text21);
        num_fijo =root.findViewById(R.id.Text23);
        setHasOptionsMenu(true);

        bb = getActivity().getIntent().getExtras();
        Llenarvista();
        return root;
    }
    private void Llenarvista(){
        final Integer id_cliente = Integer.parseInt(bb.getString("usuario"));
        Response.Listener<String> responListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    String nomcli = jsonResponse.getString("Nomcli");
                    String apecli = jsonResponse.getString("Apecli");
                    String edadcli = jsonResponse.getString("Edadcli");
                    String telecli = jsonResponse.getString("Telecli");
                    String tipo_doc = jsonResponse.getString("tipo_doc");
                    String cedula = jsonResponse.getString("cedula");
                    String genero = jsonResponse.getString("genero");
                    String ciudad_residencia = jsonResponse.getString("ciudad_residencia");
                    String dir = jsonResponse.getString("dir");
                    String ciudad_nacimiento = jsonResponse.getString("ciudad_nacimiento");
                    String telefono_fijo = jsonResponse.getString("telefono_fijo");
                    //Toast.makeText(this, "El cédula o contraseña es incorrecta.", Toast.LENGTH_LONG).show();

                    if (success)
                    {
                        nombre1.setText(nomcli);
                        apellido1.setText(apecli);
                        edad1.setText(edadcli);
                        celular1.setText(telecli);
                        clasedoc1.setText(tipo_doc);
                        numdoc1.setText(cedula);
                        genero1.setText(genero);
                        ciudad_r.setText(ciudad_residencia);
                        direcion_r.setText(dir);
                        ciudad_n.setText(ciudad_nacimiento);
                        num_fijo.setText(telefono_fijo);
                    }
                    else
                    {
                        AlertDialog.Builder hh = new AlertDialog.Builder(getActivity());
                        hh.setMessage("No hay datos del usuario")
                                .setNegativeButton("Reintentar",null)
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

        ConsultaDatosPersonales dat= new ConsultaDatosPersonales(id_cliente, responListener, error);
        RequestQueue queue= Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(dat);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
//        getActivity().getMenuInflater().inflate(R.menu.overflow,menu);
//        return true;
        inflater.inflate(R.menu.overflow, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem cerrar){
        int id = cerrar.getItemId();
        if(id==R.id.cerrar){
            Toast.makeText(getContext(), "Cerrar sesion", Toast.LENGTH_LONG).show();
            Intent ii = new Intent(getActivity(), MainActivity.class);
            startActivity(ii);
        }
        return super.onOptionsItemSelected(cerrar);
    }
}