package oreschnix.exampleapp.networking.rest;

import java.util.concurrent.Executor;

import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.converter.Converter;

/**
 * Created by Miha on 19.10.2015.
 */
public class RestClientBuilder {
    String host;
    Converter converter;
    Client client;
    Executor httpExecutor;
    Executor callbackExecutor;

    public RestClientBuilder() {
    }

    public void inject() {
    }

    public <T> RestClient<T> createRestClient(Class<T> serviceClass, RestClientComponent component) {
        this.host = component.getHost();
        this.converter = component.getConverter();
        this.client = component.getClient();

        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(this.host).setConverter(this.converter).setClient(this.client);

        if (this.httpExecutor != null) {
            builder.setExecutors(this.httpExecutor, this.callbackExecutor);
        }

        RestAdapter restAdapter = builder.build();
        return new RestClient(restAdapter.create(serviceClass));
    }

    public String getHost() {
        return this.host;
    }

    public Converter getConverter() {
        return this.converter;
    }

    public Client getClient() {
        return this.client;
    }
}
