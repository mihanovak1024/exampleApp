package oreschnix.exampleapp.networking.rest;

/**
 * Created by Miha on 19.10.2015.
 */
public class RestClient<T> {

    T apiService;

    public RestClient(T apiService) {
        this.apiService = apiService;
    }

    public T getApiService() {
        return (T) apiService;
    }

}
