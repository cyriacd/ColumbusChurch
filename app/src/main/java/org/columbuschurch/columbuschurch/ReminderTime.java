package org.columbuschurch.columbuschurch;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cyriac on 3/24/16.
 */
public class ReminderTime {
    public static final String MY_PREF = "ReminderTime";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public ReminderTime(Context context) {
        this.sharedPreferences = context.getSharedPreferences(MY_PREF, 0);
        this.editor = this.sharedPreferences.edit();
    }

    public void setEnabled(boolean enabled){
        this.editor.putBoolean("ENABLED", enabled);
        this.editor.commit();
    }

    public void setHour(int hour) {
        this.editor.putInt("HOUR", hour);
        this.editor.commit();
    }

    public void setMin(int min) {
        this.editor.putInt("MIN", min);
        this.editor.commit();
    }

    public boolean getEnabled(){
        return this.sharedPreferences.getBoolean("HOUR", true);
    }

    public int getHour() {
        return this.sharedPreferences.getInt("HOUR", 7);
    }

    public int getMin() {
        return this.sharedPreferences.getInt("MIN", 0);
    }

    public void reset() {
        this.editor.remove("HOUR");
        this.editor.remove("MIN");
        this.editor.commit();
    }

    public void clear() {
        this.editor.clear();
        this.editor.commit();
    }
}