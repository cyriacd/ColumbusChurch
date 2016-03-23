package org.columbuschurch.columbuschurch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Gallery extends AppCompatActivity {

    List<String> urls;
    Bitmap bmImg = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        Bundle extras = getIntent().getExtras();
        int mGalID = 0;
        if(extras!=null){
            mGalID = extras.getInt("GALLERY_ID");
        }
        switch (mGalID){
            case 0:
                urls = Arrays.asList(getResources().getStringArray(R.array.gallery_0));
                break;
            case 1:
                urls = Arrays.asList(getResources().getStringArray(R.array.gallery_1));
                break;
            case 2:
                urls = Arrays.asList(getResources().getStringArray(R.array.gallery_2));
                break;
            default:
                urls = Arrays.asList(getResources().getStringArray(R.array.gallery_0));
                break;
        }
        setContentView(R.layout.activity_gallery);
        TextView galleryname = (TextView) findViewById(R.id.gallery_name);
        galleryname.setText(urls.get(0));
        for(int i=2;i<urls.size();i++){
            String url;
            url = urls.get(i);
            new getPictures().execute(url);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    class getPictures extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            InputStream is;
            URL uri;
            String url = args[0];
            try {
                uri = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();
                bmImg=BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                Log.d("Picture Error", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super .onPostExecute(result);
            ImageView image;
            LinearLayout piclayout = (LinearLayout) findViewById(R.id.gallery_preview_scroll_layout);
            image = new ImageView(Gallery.this);
            image.setImageBitmap(bmImg);
            piclayout.addView(image);
            image.getLayoutParams().width=ViewGroup.LayoutParams.MATCH_PARENT;
            image.getLayoutParams().height=ViewGroup.LayoutParams.WRAP_CONTENT;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,0,20);
            image.setLayoutParams(params);
            image.setAdjustViewBounds(true);
            image.requestLayout();
        }
    }

}