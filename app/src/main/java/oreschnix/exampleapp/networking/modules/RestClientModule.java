package oreschnix.exampleapp.networking.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import oreschnix.exampleapp.core.Core;
import oreschnix.exampleapp.networking.ApiService;
import oreschnix.exampleapp.networking.component.DaggerUnsecuredClientComponent;
import oreschnix.exampleapp.networking.rest.RestClient;
import oreschnix.exampleapp.networking.rest.RestClientBuilder;
import oreschnix.exampleapp.networking.rest.RestClientComponent;

/**
 * Created by Miha on 19.10.2015.
 */
@Module
public class RestClientModule {

    private RestClientBuilder components = new RestClientBuilder();

    @Provides
    @Named(Core.REST_CLIENT)
    public RestClient provideRestClient() {
        RestClientComponent component = DaggerUnsecuredClientComponent.builder().build();

        return components.createRestClient(ApiService.class, component);
    }

}
