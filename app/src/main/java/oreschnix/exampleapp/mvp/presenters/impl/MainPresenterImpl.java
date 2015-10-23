package oreschnix.exampleapp.mvp.presenters.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.inject.Inject;

import oreschnix.exampleapp.models.Restaurant;
import oreschnix.exampleapp.mvp.interactors.MainInteractor;
import oreschnix.exampleapp.mvp.listeners.RestaurantListener;
import oreschnix.exampleapp.mvp.presenters.MainPresenter;
import oreschnix.exampleapp.mvp.views.MainView;

/**
 * Created by Miha on 2.9.2015.
 */
public class MainPresenterImpl implements MainPresenter, RestaurantListener {

    private MainView view;
    private MainInteractor interactor;

    @Inject
    public MainPresenterImpl(MainView view, MainInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void init() {
        view.initUI();
        loadRestaurants();
    }

    @Override
    public void loadRestaurants() {
        interactor.getRestaurants(this);
    }

    @Override
    public void onGetRestaurantsSuccess(ArrayList<Restaurant> restaurants) {
        Collections.sort(restaurants, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant lhs, Restaurant rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        view.showRestaurants(restaurants);
    }

    @Override
    public void onFailure(String message) {
        view.showErrorMessage(message);
    }

    @Override
    public void onNoConnection() {
        view.showNoConnectionMessage();
    }
}
