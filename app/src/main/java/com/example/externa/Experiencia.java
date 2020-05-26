package com.example.externa;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class Experiencia extends StringRequest{
    private static final String REGISTER_REQUEST_URL = "https://www.redesoft.com.co/Info_Antigua/Experiencia.php";
    private Map<String, String> params;

    public Experiencia(int id_cliente,String empresa,String sector,String subsector,String cargo,String logros,int telefono,String ciudad, Response.Listener<String> listener, Response.ErrorListener error)    {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, error);
        params = new HashMap<>();
        params.put("id_cliente", id_cliente+"");
        params.put("empresa", empresa);
        params.put("sector", sector);
        params.put("subsector", subsector);
        params.put("cargo", cargo);
        params.put("logros", logros);
        params.put("telefono", telefono+"");
        params.put("ciudad", ciudad);

    }
    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}