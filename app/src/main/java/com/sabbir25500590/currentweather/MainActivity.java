package com.sabbir25500590.currentweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv,tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et=findViewById(R.id.et);
        tv=findViewById(R.id.tv);
        tv1=findViewById(R.id.tv1);


    }

    public void get(View v){

        String apikey="c3930d9a8e9075f7a14a715c8f581b31";
        String city=et.getText().toString();
        String url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=c3930d9a8e9075f7a14a715c8f581b31";

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject object=response.getJSONObject("main");

                    String temperature=object.getString("temp");
                    Double temp=Double.parseDouble(temperature)-273.15;
                    tv.setText("Temperature of "+city+ " is "+temp.toString().substring(0,5)+"C"+"\n");

                    String mintemperature=object.getString("pressure");
                    Double minTemp=Double.parseDouble(mintemperature)-273.15;
                    tv1.setText("MinTemperature of "+city+ " is "+minTemp.toString().substring(0,5)+"C \n");


                } catch (JSONException e) {
                   Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(request);
    }
}