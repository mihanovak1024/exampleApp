package oreschnix.exampleapp;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import oreschnix.exampleapp.core.Core;
import oreschnix.exampleapp.dagger.components.DaggerAppComponent;

/**
 * Created by Miha on 16.10.2015.
 */
public class ExampleApp extends Application {

    private static ExampleApp instance;

    protected Gson gson;

    @Override
    public synchronized void onCreate() {
        super.onCreate();
        instance = this;

        Core.getInstance().inject(DaggerAppComponent.create());
    }

    public static ExampleApp getInstance() {
        return instance;
    }

    private void setupGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }
}
