package oreschnix.exampleapp.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import oreschnix.exampleapp.ExampleApp;

/**
 * Created by Miha on 23.9.2015.
 */
public class PreferenceUtils {

    public static SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(ExampleApp.getInstance());
    }

}
