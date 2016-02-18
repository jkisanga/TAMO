package com.tanapaapp.tzchoice.tamo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import data.Constants;
import data.ParseJSON;
import data.RVAdapter;
import modal.Park;

/**
 * Created by tzchoice on 1/21/2016.
 */
public class ActivityFragment extends Fragment {
    private   RVAdapter adapter;

    private void initializeData(){

        StringRequest stringRequest = new StringRequest(Constants.JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        );

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    private void showJSON(String response) {
        ParseJSON pj = new ParseJSON(response);
        pj.parseJSON();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        return inflater.inflate(R.layout.activity_fragment, container, false);
    }
}
