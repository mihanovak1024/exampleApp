package oreschnix.exampleapp.dagger.modules;

import dagger.Module;
import dagger.Provides;
import oreschnix.exampleapp.mvp.interactors.MainInteractor;
import oreschnix.exampleapp.mvp.interactors.impl.MainInteractorImpl;
import oreschnix.exampleapp.mvp.presenters.MainPresenter;
import oreschnix.exampleapp.mvp.presenters.impl.MainPresenterImpl;
import oreschnix.exampleapp.mvp.views.MainView;

/**
 * Created by Miha on 2.9.2015.
 */
@Module
public class MainModule {

    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    public MainView provideView() {
        return view;
    }

    @Provides
    public MainPresenter providePresenter(MainPresenterImpl impl) {
        return impl;
    }

    @Provides
    public MainInteractor provideInteractor(MainInteractorImpl interactor) {
        return interactor;
    }

}
