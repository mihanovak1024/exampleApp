package oreschnix.exampleapp.networking.rest;

import retrofit.client.Client;
import retrofit.converter.Converter;

/**
 * Created by Miha on 19.10.2015.
 */
public interface RestClientComponent {

    String getHost();

    Converter getConverter();

    Client getClient();

}
