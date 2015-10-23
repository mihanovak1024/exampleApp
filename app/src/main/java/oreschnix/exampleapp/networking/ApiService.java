package oreschnix.exampleapp.networking;

import java.util.ArrayList;

import oreschnix.exampleapp.models.Restaurant;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Miha on 19.10.2015.
 */
public interface ApiService {

    @GET("/restaurants")
    void getRestaurants(Callback<ArrayList<Restaurant>> restaurantsCallback);

}
