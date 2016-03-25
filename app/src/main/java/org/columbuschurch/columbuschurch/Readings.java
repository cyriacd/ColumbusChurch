package org.columbuschurch.columbuschurch;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Readings extends AppCompatActivity {
    int DAY = -1;
    Reading reading = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            DAY = getIntent().getExtras().getInt("DAY");
        }catch(Exception e){
            e.printStackTrace();
        }
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if(DAY == -1){
            Calendar day = Calendar.getInstance();
            DAY = day.get(Calendar.DAY_OF_WEEK);
        }
        //TODO Remove this line:
        DAY=0;
        new getReadings().execute(Integer.toString(DAY));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Toast.makeText(this, "UP Pressed", Toast.LENGTH_LONG).show();
//                Intent startHome =
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    class getReadings extends AsyncTask<String, Reading, Reading> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        protected Reading doInBackground(String... args) {
            String day = "day"+args[0];
            if(day!="day-1"){
                InputStream is;
                try {
                    URL uri = new URL("http://cyriacdomini.com/extras/readings.xml");
                    HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    is = conn.getInputStream();
                    XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = xmlFactoryObject.newPullParser();
                    parser.setInput(is, null);
                    String name;
                    //TODO: CHECK THIS
//                    List<Reading> allReadings = new ArrayList<Reading>();
                    int eventType = parser.getEventType();
                    while(eventType!=XmlPullParser.END_DOCUMENT){
                        
                        name = parser.getName();
                        if(eventType==XmlPullParser.START_TAG) {
                            if ("reading1".equals(name)) {
                                if(parser.next() == XmlPullParser.TEXT) {
                                    reading.read1 = parser.getText();
                                }
                            } else if ("reading2".equals(name)) {
                                if(parser.next() == XmlPullParser.TEXT) {
                                    reading.read2 = parser.getText();
                                }
                            } else if ("reading3".equals(name)) {
                                if(parser.next() == XmlPullParser.TEXT) {
                                    reading.read3 = parser.getText();
                                }
                            } else if ("gospel".equals(name)) {
                                if(parser.next() == XmlPullParser.TEXT) {
                                    reading.gospel = parser.getText();
                                }
                            } else if (name.startsWith(day)) {
                                reading = new Reading();
                            }
                        }else if(eventType==XmlPullParser.END_TAG){
                            if(day.equals(name)){
                                break;
                            }
                        }
                        eventType = parser.next();
                    }
                    Log.d("READINGS: ","DONE");
                } catch (Exception e) {
                    Log.d("Readings Error", e.toString());
                    e.printStackTrace();
                }
            }
            return reading;
        }

        @Override
        protected void onPostExecute(Reading result) {
            super .onPostExecute(result);
            LinearLayout readingPage = (LinearLayout) findViewById(R.id.reading_content);
            if(!reading.read1.equals("")){
                TextView reading1Title = new TextView(Readings.this);
                reading1Title.setText(getText(R.string.first_reading_heading));
                TextView reading1 = new TextView(Readings.this);
                reading1.setText(Html.fromHtml(reading.read1));
                readingPage.addView(reading1);
            }
            if(!reading.read2.equals("")){
                TextView reading2Title = new TextView(Readings.this);
                reading2Title.setText(getText(R.string.second_reading_heading));
                TextView reading2 = new TextView(Readings.this);
                reading2.setText(Html.fromHtml(reading.read2));
                readingPage.addView(reading2);
            }
            if(!reading.read3.equals("")){
                TextView reading3Title = new TextView(Readings.this);
                reading3Title.setText(getText(R.string.third_reading_heading));
                TextView reading3 = new TextView(Readings.this);
                reading3.setText(Html.fromHtml(reading.read3));
                readingPage.addView(reading3);
            }
            if(!reading.gospel.equals("")){
                TextView gospelTitle = new TextView(Readings.this);
                gospelTitle.setText(getText(R.string.gospel_reading_heading));
                TextView gospel = new TextView(Readings.this);
                gospel.setText(Html.fromHtml(reading.gospel));
                readingPage.addView(gospel);
            }
        }
    }
}
