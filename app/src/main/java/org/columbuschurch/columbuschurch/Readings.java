package org.columbuschurch.columbuschurch;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Readings extends AppCompatActivity {
    int DAY = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DAY = getIntent().getExtras().getInt("DAY");
        Log.d("DAY",Integer.toString(DAY));
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException e){

        }
        new getReadings().execute(Integer.toString(DAY));
    }

    class getReadings extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            String day = args[0];
//            try {
//                uri = new URL(url);
//                HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
//                conn.setDoInput(true);
//                conn.connect();
//                is = conn.getInputStream();
//                bmImg= BitmapFactory.decodeStream(is);
//            } catch (Exception e) {
//                Log.d("Picture Error", e.toString());
//            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super .onPostExecute(result);
            LinearLayout reading = (LinearLayout) findViewById(R.id.reading_content);
            TextView tv = new TextView(Readings.this);
            tv.setText("Day"+Integer.toString(DAY));
            reading.addView(tv);
//            ImageView image;
//            LinearLayout piclayout = (LinearLayout) findViewById(R.id.gallery_preview_scroll_layout);
//            image = new ImageView(Gallery.this);
//            image.setImageBitmap(bmImg);
//            piclayout.addView(image);
//            image.getLayoutParams().width= ViewGroup.LayoutParams.MATCH_PARENT;
//            image.getLayoutParams().height=ViewGroup.LayoutParams.WRAP_CONTENT;
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//            params.setMargins(0,0,0,20);
//            image.setLayoutParams(params);
//            image.setAdjustViewBounds(true);
//            image.requestLayout();
        }
    }

}
