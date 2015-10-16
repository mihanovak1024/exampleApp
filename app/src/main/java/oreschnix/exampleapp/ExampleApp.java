package oreschnix.exampleapp;

import android.app.Application;

/**
 * Created by Miha on 16.10.2015.
 */
public class ExampleApp extends Application{

    private static ExampleApp instance;


    @Override
    public synchronized void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static ExampleApp getInstance() {
        return instance;
    }
}
