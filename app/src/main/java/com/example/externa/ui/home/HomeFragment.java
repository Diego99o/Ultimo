package com.example.externa.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.externa.Bienvenidos;
import com.example.externa.Experiencia;
import com.example.externa.MainActivity;
import com.example.externa.R;
import com.example.externa.Registro;
import com.example.externa.Servidorweb1;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {
    EditText empresa1,sector1,subsector1,cargo1,logros1,telefono1,ciudad1;
    Bundle tt;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        empresa1 = root.findViewById(R.id.editText);
        sector1 = root.findViewById(R.id.editText2);
        subsector1 =root.findViewById(R.id.editText3);
        cargo1 =root.findViewById(R.id.editText7);
        logros1 =root.findViewById(R.id.editText9);
        telefono1  =root.findViewById(R.id.editText12);
        ciudad1  =root.findViewById(R.id.editText11);
        tt = getActivity().getIntent().getExtras();
        Button button = root.findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Integer id_cliente = Integer.parseInt(tt.getString("usuario"));
                final String empresa = empresa1.getText().toString();
                final String sector = sector1.getText().toString();
                final String subsector = subsector1.getText().toString();
                final String nombre = cargo1.getText().toString();
                final String logros = logros1.getText().toString();
                final Integer telefono = Integer.parseInt(telefono1.getText().toString());
                final String ciudad = ciudad1.getText().toString();


                Log.d("REGISTRO", "Entro metodo");

                    Response.Listener<String> responListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
//                                    Intent pasar = new Intent(getActivity(), Bienvenidos.class);
                                    empresa1.setText("");
                                    sector1.setText("");
                                    subsector1.setText("");
                                    cargo1.setText("");
                                    logros1.setText("");
                                    telefono1.setText("");
                                    ciudad1.setText("");
                                    Toast.makeText(getContext(), "Guardado ", Toast.LENGTH_LONG).show();
//                                    startActivity(pasar);
                                } else {
                                    AlertDialog.Builder hh = new AlertDialog.Builder(getActivity());
                                    hh.setMessage("No se guardo")
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

                    Experiencia datos = new Experiencia(id_cliente,empresa,sector,subsector,nombre,logros,telefono,ciudad, responListener, error);
                    RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    queue.add(datos);
            }
        });
        return root;
    }
}