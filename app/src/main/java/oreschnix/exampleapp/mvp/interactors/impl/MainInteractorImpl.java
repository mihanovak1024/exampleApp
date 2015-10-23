package oreschnix.exampleapp.mvp.interactors.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import co.infinum.androidgoodies.ConnectionUtils;
import oreschnix.exampleapp.ExampleApp;
import oreschnix.exampleapp.R;
import oreschnix.exampleapp.core.ExampleAppCore;
import oreschnix.exampleapp.models.Restaurant;
import oreschnix.exampleapp.mvp.interactors.MainInteractor;
import oreschnix.exampleapp.mvp.listeners.RestaurantListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Miha on 19.10.2015.
 */
public class MainInteractorImpl implements MainInteractor {

    @Inject
    public MainInteractorImpl() {
    }

    @Override
    public void getRestaurants(final RestaurantListener listener) {
        if (ConnectionUtils.hasNetworkConnection(ExampleApp.getInstance())) {
            ExampleAppCore.getRestApiService().getRestaurants(new Callback<ArrayList<Restaurant>>() {
                @Override
                public void success(ArrayList<Restaurant> restaurants, Response response) {
                    try {

                        listener.onGetRestaurantsSuccess(restaurants);
                    } catch (Exception e) {
                        listener.onFailure(ExampleApp.getInstance().getString(R.string.on_failure_message));
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    //listener.onFailure(ExampleApp.getInstance().getString(R.string.on_failure_message));
                    listener.onFailure(error.toString());
                }
            });
        } else {
            listener.onNoConnection();
        }
    }
}
