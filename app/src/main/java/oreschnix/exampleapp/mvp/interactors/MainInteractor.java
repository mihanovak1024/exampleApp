package oreschnix.exampleapp.mvp.interactors;

import oreschnix.exampleapp.mvp.listeners.RestaurantListener;

/**
 * Created by Miha on 19.10.2015.
 */
public interface MainInteractor {

    void getRestaurants(RestaurantListener listener);

}
