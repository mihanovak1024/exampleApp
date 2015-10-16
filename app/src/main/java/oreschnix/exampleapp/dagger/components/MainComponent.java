package oreschnix.exampleapp.dagger.components;

import dagger.Component;
import oreschnix.partyapp.activities.MainActivity;
import oreschnix.partyapp.dagger.modules.MainModule;

/**
 * Created by Miha on 2.9.2015.
 */
@Component (modules = MainModule.class)
public interface MainComponent {

    void init(MainActivity activity);

}
