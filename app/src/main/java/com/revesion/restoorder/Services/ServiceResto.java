package com.revesion.restoorder.Services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.revesion.restoorder.Adapters.MyRestoRecyclerViewAdapter;
import com.revesion.restoorder.Entities.Resto;
import com.revesion.restoorder.Fragments.RestoFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServiceResto {

    public void getListResto(Context ctx, final RecyclerView rcv, final RestoFragment.OnListFragmentInteractionListener mLsitenr) {
// Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(ctx);
        String url = "http://startdevelopment.fr/technical-test/";

// Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            ArrayList<Resto> listResto = new ArrayList<Resto>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                Resto resto = new Resto();

                                JSONObject objectJson = jsonArray.getJSONObject(i);

                                resto.setId(objectJson.getInt("Id"));
                                resto.setName(objectJson.getString("name"));
                                resto.setCuisine(objectJson.getString("cuisine"));
                                resto.setAddress(objectJson.getString("address"));
                                resto.setMin_order_price("Min Order $" + objectJson.getString("minimum-order-price"));
                                resto.setImage(objectJson.getString("logo"));

                                Log.d("5000", objectJson.getString("name"));
                                listResto.add(resto);

                            }
                            rcv.setAdapter(new MyRestoRecyclerViewAdapter(listResto, mLsitenr));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
