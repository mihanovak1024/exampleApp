package oreschnix.exampleapp.dagger.components;

import dagger.Component;
import oreschnix.partyapp.activities.IntroActivity;
import oreschnix.partyapp.dagger.modules.IntroModule;

/**
 * Created by Miha on 3.9.2015.
 */
@Component (modules = IntroModule.class)
public interface IntroComponent {
    void init(IntroActivity activity);
}
