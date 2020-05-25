package com.example.externa;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Servidorweb1 extends StringRequest{
    private static final String REGISTER_REQUEST_URL = "https://www.redesoft.com.co/Info_Antigua/register1.php";
    private Map<String, String> params;
    public Servidorweb1(String Nomcli, String ApeCli, int Edadcli, String Correocli,int Telecli, String Clavecli, Response.Listener<String> listener, Response.ErrorListener error)    {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, error);
        params = new HashMap<>();
        params.put("Nomcli", Nomcli);
        params.put("Apecli", ApeCli);
        params.put("Edadcli", Edadcli+"");
        params.put("Correocli", Correocli);
        params.put("Telecli", Telecli+"");
        params.put("Clavecli", Clavecli);
    }
    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
