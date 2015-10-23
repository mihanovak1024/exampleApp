package oreschnix.exampleapp.dagger.components;

import dagger.Component;
import oreschnix.exampleapp.core.CoreComponent;
import oreschnix.exampleapp.dagger.modules.AppModule;

/**
 * Created by Miha on 19.10.2015.
 */
@Component(modules = AppModule.class)
public interface AppComponent extends CoreComponent {
}
