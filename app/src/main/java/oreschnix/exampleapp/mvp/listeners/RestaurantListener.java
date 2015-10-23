package oreschnix.exampleapp.mvp.listeners;

import java.util.ArrayList;

import oreschnix.exampleapp.models.Restaurant;

/**
 * Created by Miha on 19.10.2015.
 */
public interface RestaurantListener extends BaseListener {

    void onGetRestaurantsSuccess(ArrayList<Restaurant> restaurants);
}
