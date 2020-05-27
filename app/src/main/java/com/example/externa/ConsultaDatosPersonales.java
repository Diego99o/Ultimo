package com.example.externa;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ConsultaDatosPersonales extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://www.redesoft.com.co/Info_Antigua/ConsultaDatosPersonales.php";
    private Map<String, String> params;

    public ConsultaDatosPersonales(int id_cliente, Response.Listener<String> listener, Response.ErrorListener error)
    {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, error);
        params = new HashMap<>();
        params.put("id_cliente", id_cliente+"");
    }
    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
