package com.revesion.restoorder.Services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.revesion.restoorder.Adapters.MyMenuRecyclerViewAdapter;
import com.revesion.restoorder.Entities.Menu;
import com.revesion.restoorder.Fragments.MenuFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServiceMenu {

    public void getMenu(int idResto, Context ctx, final RecyclerView rcv, final MenuFragment.OnListFragmentInteractionListener mListner) {

        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(ctx);
        String url = "http://startdevelopment.fr/technical-test/?id=" + idResto;

// Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        try {
                            JSONArray menuArrayJson = new JSONArray(response);

                            ArrayList<Menu> listMenu = new ArrayList<Menu>();

                            for (int i = 0; i < menuArrayJson.length(); i++) {
                                Menu m = new Menu();
                                JSONObject objetJson = menuArrayJson.getJSONObject(i);

                                m.setNameMenu(objetJson.getString("name"));
                                m.setDescription(objetJson.getString("description"));
                                m.setPrice("$" + objetJson.getString("price"));
                                // m.setPhotoMenu(objetJson.getString("photo"));

                                listMenu.add(m);
                            }
                            rcv.setAdapter(new MyMenuRecyclerViewAdapter(listMenu, mListner));


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
