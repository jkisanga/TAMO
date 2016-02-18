package com.tanapaapp.tzchoice.tamo;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import data.Constants;

public class ParkDetailActivity extends AppCompatActivity {

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    Integer parkId;
    String parkName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_park);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        TextView tvLongDesc = (TextView) findViewById(R.id.longDesc);
        NetworkImageView imageIcon = (NetworkImageView) findViewById(R.id.imageIcon1);

        //getting intent
        Bundle bundle = getIntent().getExtras();
        String LongDesc = bundle.getString(Constants.PARK_LONG_DESC);
        String imageUrl = bundle.getString(Constants.PARK_IMAGE_ICON);
        parkName = bundle.getString(Constants.PARK_NAMES);
         parkId = (Integer) bundle.get(Constants.PARK_ID);

        //load image
        imageIcon.setImageUrl(imageUrl, imageLoader);
        collapsingToolbarLayout.setTitle(parkName.toString());

        tvLongDesc.setText(LongDesc.toString());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParkDetailActivity.this, ParkReviewActivity.class);
                intent.putExtra(Constants.PARK_ID, parkId);
                intent.putExtra(Constants.PARK_NAMES, parkName);
                startActivity(intent);
//                Snackbar.make(findViewById(R.id.coordinator), "This is Snackbar", Snackbar.LENGTH_LONG).setAction("Action", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(ParkDetailActivity.this, "Action Snackbar", Toast.LENGTH_LONG).show();
//
//                    }
//                });
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_park, menu);
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
}
