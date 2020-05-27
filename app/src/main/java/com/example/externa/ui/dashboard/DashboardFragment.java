package com.example.externa.ui.dashboard;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.example.externa.R;

import org.json.JSONException;
import org.json.JSONObject;

public class DashboardFragment extends Fragment {

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
        Llenarvista();
        return root;
    }
    private void Llenarvista(){

        Response.Listener<String> responListener = new Response<>().Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    String id = jsonResponse.getString("Idcli");
                    //Toast.makeText(this, "El cédula o contraseña es incorrecta.", Toast.LENGTH_LONG).show();

                    if (success)
                    {

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

        ConsultaDatosPersonales dat= new ConsultaDatosPersonales(Nomcli, Apecli,Edadcli, Telecli,tipo_doc,cedula,genero, ciudad_residencia,dir, ciudad_nacimiento,telefono_fijo, responListener, error);
        RequestQueue queue= Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(dat);
    }
}