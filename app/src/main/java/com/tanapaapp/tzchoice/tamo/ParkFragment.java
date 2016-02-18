package com.tanapaapp.tzchoice.tamo;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
public class ParkFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "ParkFragment";
    private SwipeRefreshLayout swipeRefreshLayout;
    private RVAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.park_fragment, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(40);
        swipeRefreshLayout.setColorScheme(android.R.color.holo_green_dark,
                android.R.color.holo_red_dark,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        fetchParks();
                                        // stopping swipe refresh
                                        swipeRefreshLayout.setRefreshing(false);
                                    }
                                }
        );
        recyclerView = (RecyclerView) v.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return v;
    }

    private void fetchParks() {
        StringRequest stringRequest = new StringRequest(Constants.JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                inflateRecyclerView(response);
                // stopping swipe refresh
                swipeRefreshLayout.setRefreshing(false);
                // loading.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "No internet access", Toast.LENGTH_LONG).show();

                // stopping swipe refresh
                swipeRefreshLayout.setRefreshing(false);

            }
        }

        );

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);



    }

    private void inflateRecyclerView(String response){
        ParseJSON pj = new ParseJSON(response);
        pj.parseJSON();

        adapter = new RVAdapter(getActivity(), ParseJSON.rating_caches, ParseJSON.rating_counts, ParseJSON.short_desc,
                ParseJSON.long_desc, ParseJSON.icon, ParseJSON.names, ParseJSON.ids);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        fetchParks();
    }



}

