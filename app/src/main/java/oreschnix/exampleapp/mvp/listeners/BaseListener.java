package oreschnix.exampleapp.mvp.listeners;

/**
 * Created by Miha on 19.10.2015.
 */
public interface BaseListener {

    void onFailure(String message);

    void onNoConnection();

}
