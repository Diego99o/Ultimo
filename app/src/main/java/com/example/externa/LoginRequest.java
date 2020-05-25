package com.example.externa;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends  StringRequest{
    private static final String REGISTER_REQUEST_URL = "https://www.redesoft.com.co/Info_Antigua/Loginapp.php";
    private Map<String, String> params;

    public LoginRequest(String Correocli, String Clavecli, Response.Listener<String> listener)
    {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Correocli", Correocli);
        params.put("Clavecli", Clavecli);
    }
    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}