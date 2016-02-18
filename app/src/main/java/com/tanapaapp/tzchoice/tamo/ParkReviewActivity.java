package com.tanapaapp.tzchoice.tamo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import data.Constants;
import data.ParseJSON;
import data.ParseReviewJSON;
import data.RVAdapter;
import data.ReviewAdapter;
import modal.Activity;

public class ParkReviewActivity extends AppCompatActivity {
    private ReviewAdapter adapter;
    private ProgressDialog loading;
    RecyclerView recyclerView;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_review);
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);
        Bundle bundle = getIntent().getExtras();
        Integer parkId = bundle.getInt(Constants.PARK_ID);
       btnSend = (Button) findViewById(R.id.btnSend);
        String Review_Url = Constants.JSON_REVIEWS_URL + parkId;

        StringRequest stringRequest = new StringRequest(Review_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                inflateRecyclerView(response);
                loading.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
            }
        }

        );
        Log.d("Review_url", Review_Url);
        RequestQueue requestQueue = Volley.newRequestQueue(ParkReviewActivity.this);
        requestQueue.add(stringRequest);

        recyclerView = (RecyclerView) findViewById(R.id.list_view_comments);
        recyclerView.setLayoutManager(new LinearLayoutManager(ParkReviewActivity.this));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_park_review, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void inflateRecyclerView(String response){

        ParseReviewJSON pj = new ParseReviewJSON(response);
        pj.ParseReviewJSON();

        adapter = new ReviewAdapter(ParkReviewActivity.this, ParseReviewJSON.rating, ParseReviewJSON.comments);

        recyclerView.setAdapter(adapter);
    }
}
