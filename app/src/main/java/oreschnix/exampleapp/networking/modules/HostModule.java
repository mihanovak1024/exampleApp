package oreschnix.exampleapp.networking.modules;

import dagger.Module;
import dagger.Provides;
import oreschnix.exampleapp.ExampleApp;
import oreschnix.exampleapp.R;

/**
 * Created by Miha on 19.10.2015.
 */
@Module
public class HostModule {

    @Provides
    public String provideHost() {
        return ExampleApp.getInstance().getString(R.string.host_endpoint);
    }
}