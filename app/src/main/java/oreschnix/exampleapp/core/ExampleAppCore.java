package oreschnix.exampleapp.core;

import oreschnix.exampleapp.networking.ApiService;

/**
 * Created by Miha on 19.10.2015.
 */
public class ExampleAppCore extends Core {

    public static ApiService getRestApiService() {
        return getRestClient(ApiService.class).getApiService();
    }

}
