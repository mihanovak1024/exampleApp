package oreschnix.exampleapp.mvp.views;

import java.util.ArrayList;

import oreschnix.exampleapp.models.Restaurant;

/**
 * Created by Miha on 2.9.2015.
 */
public interface MainView {

    void initUI();

    void showRestaurants(ArrayList<Restaurant> restaurants);

    void showNoConnectionMessage();

    void showErrorMessage(String message);

}
