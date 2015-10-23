package oreschnix.exampleapp.core;

import javax.inject.Inject;
import javax.inject.Named;

import oreschnix.exampleapp.networking.rest.RestClient;

/**
 * Created by Miha on 19.10.2015.
 */
public class Core {

    public static final String REST_CLIENT = "RestClient";

    protected static Core instance;

    @Inject
    @Named("RestClient")
    RestClient restClient;

    public Core() {
    }

    public static Core getInstance() {
        if (instance == null) {
            instance = new Core();
        }
        return instance;
    }

    public void inject(CoreComponent component) {
        this.restClient = component.getRestClient();
    }

    public static <A> RestClient<A> getRestClient(Class<A> type) {
        return instance.restClient;
    }
}
