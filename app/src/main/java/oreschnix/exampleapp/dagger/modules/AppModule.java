package oreschnix.exampleapp.dagger.modules;

import dagger.Module;
import oreschnix.exampleapp.networking.modules.RestClientModule;

/**
 * Created by Miha on 19.10.2015.
 */
@Module(includes = RestClientModule.class)
public class AppModule {
}
