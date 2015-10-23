package oreschnix.exampleapp.core;

import javax.inject.Named;

import oreschnix.exampleapp.networking.rest.RestClient;

/**
 * Created by Miha on 19.10.2015.
 */
public interface CoreComponent {

    @Named("RestClient")
    RestClient getRestClient();

}
