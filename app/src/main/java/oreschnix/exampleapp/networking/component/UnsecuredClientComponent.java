package oreschnix.exampleapp.networking.component;

import dagger.Component;
import oreschnix.exampleapp.networking.modules.GsonConverterModule;
import oreschnix.exampleapp.networking.modules.HostModule;
import oreschnix.exampleapp.networking.modules.RestClientModule;
import oreschnix.exampleapp.networking.modules.UnsecuredClientModule;
import oreschnix.exampleapp.networking.rest.RestClientComponent;

/**
 * Created by Miha on 19.10.2015.
 */
@Component(modules = {
        GsonConverterModule.class,
        HostModule.class,
        RestClientModule.class,
        UnsecuredClientModule.class
})
public interface UnsecuredClientComponent extends RestClientComponent {
}
