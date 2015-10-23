package oreschnix.exampleapp.dagger.modules;

import dagger.Module;
import dagger.Provides;
import oreschnix.exampleapp.mvp.presenters.IntroPresenter;
import oreschnix.exampleapp.mvp.presenters.impl.IntroPresenterImpl;
import oreschnix.exampleapp.mvp.views.IntroView;

/**
 * Created by Miha on 3.9.2015.
 */
@Module
public class IntroModule {

    private IntroView view;

    public IntroModule(IntroView view) {
        this.view = view;
    }

    @Provides
    public IntroPresenter providePresenter(IntroPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    public IntroView providesView() {
        return view;
    }
}
