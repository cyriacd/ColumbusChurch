package org.columbuschurch.columbuschurch;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Credentials;
import android.support.annotation.NonNull;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ChurchNotification extends IntentService {

    public ChurchNotification() {
        super("ChurchNotification");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        InputStream is;
        Calendar today = Calendar.getInstance();
        int date = today.get(Calendar.DAY_OF_MONTH);
        int month = today.get(Calendar.MONTH);
        int monthIterator = -1;
        int dayIterator = -1;
        List<String> names = new ArrayList<String>();
        String birthdaymsg = "";
        try {
            URL uri = new URL("http://cyriacdomini.com/extras/blist.xml");
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();

            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream();
            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myParser = xmlFactoryObject.newPullParser();
            myParser.setInput(is, null);
            int event = myParser.getEventType();
            String currentPerson ="";
            while (event != XmlPullParser.END_DOCUMENT)
            {
                String name=myParser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.END_TAG:
                        if(name.equals("person")){
                            currentPerson =myParser.getAttributeValue(null,"name");
                            Log.d("Name: ",currentPerson);
                            try {
                                Log.d("TAG Name", currentPerson);
                                names.add(currentPerson);
                                Log.d("TAG List", names.toString());
                            }catch (Exception e){
                                Log.d("Name Error", e.toString());
                            }
                        }
                        break;
                }
                event = myParser.next();
            }
        } catch (Exception e) {
            Log.d("Birthday Error", e.toString());
            e.printStackTrace();
        }
        Log.d("DATE",Integer.toString(date)+"/"+Integer.toString(month));
        sendNotification("Today's Readings");
    }

    private void sendNotification(String msg) {
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle("Columbus Church")
                        .setContentText(msg);
        int mNotificationId=1;
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId,mBuilder.build());
    }

}
