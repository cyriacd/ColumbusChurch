package org.columbuschurch.columbuschurch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        setContentView(R.layout.activity_gallery);
        TextView galleryname = (TextView) findViewById(R.id.gallery_name);
        urls = Arrays.asList(getResources().getStringArray(R.array.gallery_1));
        galleryname.setText(urls.get(0));
        for(int i=2;i<urls.size();i++){
            String url;
            url = urls.get(i);
            new getPictures().execute(url);
        }
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
            View parent = (View)image.getParent();
            int width = parent.getWidth();
            int height = (int)(width*1.0);
            try {
                double ratio = image.getMeasuredWidth() / image.getMeasuredHeight();
                height = (int) (width / ratio);
            }catch(Exception e){
                Log.d("EXCEPTION IMAGE:", e.toString());
            }
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(width, height);
            image.setLayoutParams(lp1);
            piclayout.removeView(image);
            piclayout.addView(image);
        }
    }

}