package oreschnix.exampleapp.dagger.components;

import dagger.Component;
import oreschnix.exampleapp.activities.IntroActivity;
import oreschnix.exampleapp.dagger.modules.IntroModule;

/**
 * Created by Miha on 3.9.2015.
 */
@Component(modules = IntroModule.class)
public interface IntroComponent {
    void init(IntroActivity activity);
}
