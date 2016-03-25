package org.columbuschurch.columbuschurch;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class CurrentReadingNotification extends Service {

//    final static String ACTION = "NotifyServiceAction";
//    final static String STOP_SERVICE = "";
//    final static int RQS_STOP_SERVICE = 1;
//
//    NotifyServiceReceiver notifyServiceReceiver;
//
//    private static final int MY_NOTIFICATION_ID=1;
//    private NotificationManager notificationManager;
//    private Notification myNotification;
//    private final String myBlog = "http://android-er.blogspot.com/";

    @Override
    public void onCreate() {
// TODO Auto-generated method stub
//        notifyServiceReceiver = new NotifyServiceReceiver();
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Running", Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(getApplicationContext(), Readings.class);
//        Calendar day = Calendar.getInstance();
//        int DAY = day.get(Calendar.DAY_OF_WEEK);
//        Log.d("DAY TODAY:" , Integer.toString(DAY));
//        myIntent.putExtra("DAY",DAY);
        myIntent.putExtra("UP",true);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                myIntent,
                0);

        Notification myNotification = new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle("Today's Bible Readings")
                .setContentText("Click Here to read today's Bible readings")
                .setTicker("Today's Bible reading")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_notification)
                .build();
        NotificationManager notificationManager =
                (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, myNotification);
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
// TODO Auto-generated method stub
//        this.unregisterReceiver(notifyServiceReceiver);
        Toast.makeText(this, "Service Running", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) {
// TODO Auto-generated method stub
        return null;
    }

//    public class NotifyServiceReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context arg0, Intent arg1) {
//
//        }
//
//    }

}
