package com.example.externa;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ConsultaDatosPersonales extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://www.redesoft.com.co/Info_Antigua/ConsultaDatosPersonales.php";
    private Map<String, String> params;

    public ConsultaDatosPersonales(String Nomcli, String Apecli, int Edadcli, int Telecli,String tipo_doc, int cedula, String genero, String ciudad_residencia,String dir, String ciudad_nacimiento,int telefono_fijo, Response.Listener<String> listener, Response.ErrorListener error)
    {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, error);
        params = new HashMap<>();
        params.put("Nomcli", Nomcli);
        params.put("Apecli", Apecli);
        params.put("Edadcli", Edadcli+"");
        params.put("Telecli", Telecli+"");
        params.put("tipo_doc", tipo_doc);
        params.put("cedula", cedula+"");
        params.put("genero", genero);
        params.put("ciudad_residencia", ciudad_residencia);
        params.put("dir", dir);
        params.put("ciudad_nacimiento", ciudad_nacimiento);
        params.put("telefono_fijo", telefono_fijo+"");
    }
    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
