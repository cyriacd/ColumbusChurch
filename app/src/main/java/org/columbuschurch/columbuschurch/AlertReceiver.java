package org.columbuschurch.columbuschurch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlertReceiver extends BroadcastReceiver {

    final public static String ONE_TIME = "onetime";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(ONE_TIME, "Executed Tasks.Java File");
        //Some task here for every morning
//        Toast.makeText(context, "Start Displaying Pictures", Toast.LENGTH_LONG).show();
        Intent startNotification = new Intent(context, CurrentReadingNotification.class);
        context.startService(startNotification);
    }
}