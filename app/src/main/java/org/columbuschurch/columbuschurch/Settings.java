package org.columbuschurch.columbuschurch;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

public class Settings extends AppCompatActivity {

    private PendingIntent pendingIntent;
    private AlarmManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent alarmIntent = new Intent(this, AlertReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.columbuschurch.prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean("ENABLED", true);
//        editor.commit();

        Switch enableReminder = (Switch) findViewById(R.id.enable_reminder);
        enableReminder.setChecked(sharedPreferences.getBoolean("ENABLED",true));
        enableReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("CHANGED", "VALUES");
                Context context = getApplicationContext();
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.columbuschurch.prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("ENABLED",isChecked);
                editor.apply();
//                Calendar updateTime = Calendar.getInstance();
//                updateTime.set(Calendar.HOUR_OF_DAY, 18);
//                updateTime.set(Calendar.MINUTE, 40);
//                Intent intent = new Intent(context, AlertReceiver.class);
//                PendingIntent recurringDownload = PendingIntent.getBroadcast(context,
//                        0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//                AlarmManager alarms = (AlarmManager) context.getSystemService(
//                        Context.ALARM_SERVICE);
//                alarms.setInexactRepeating(AlarmManager.RTC_WAKEUP,
//                        updateTime.getTimeInMillis(),
//                        AlarmManager.INTERVAL_DAY, recurringDownload);
//
//                // do something, the isChecked will be
//                // true if the switch is in the On position
                if(isChecked){
                    setAlert(findViewById(android.R.id.content));
                }else{
                    cancelAlarm(findViewById(android.R.id.content));
                }
            }
        });
    }
    public void setAlert(View v){
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 10000;
        manager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),interval,pendingIntent);
        Toast.makeText(this,"Alarm Set",Toast.LENGTH_SHORT).show();
    }
    public void cancelAlarm(View view) {
        if (manager != null) {
            manager.cancel(pendingIntent);
            Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
        }
    }
}
