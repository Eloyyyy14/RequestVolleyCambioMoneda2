package com.example.eloyyyyyyy.requestvolleycambiomoneda2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    //IP del profe
    private static final String url="http://192.168.31.7/latest.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1=(TextView)findViewById(R.id.tv1);

        RequestQueue request= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        String cad=" ";
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Log.d("RESPONSE", response.toString(0));
                    JSONObject jsonObjectPrincipal=new JSONObject(response.toString(0));

                    cad="Moneda base: "+ jsonObjectPrincipal.getString("base");

                    cad +="\nCambio a Euros(EUR): "+ jsonObjectPrincipal.getJSONObject("rates").getString("EUR")+ " €";

                    Timestamp timestamp=new Timestamp(jsonObjectPrincipal.getLong("timestamp"));
                    Date date=new Date(timestamp.getTime());
                    cad += "\nFecha :"+ date;

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                tv1.setText(cad);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        request.add(jsonObjectRequest);
    }
}
