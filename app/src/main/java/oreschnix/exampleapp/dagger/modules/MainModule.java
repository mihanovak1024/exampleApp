package oreschnix.exampleapp.dagger.modules;

import dagger.Module;
import dagger.Provides;
import oreschnix.partyapp.mvp.presenters.MainPresenter;
import oreschnix.partyapp.mvp.presenters.impl.MainPresenterImpl;
import oreschnix.partyapp.mvp.views.MainView;

/**
 * Created by Miha on 2.9.2015.
 */
@Module
public class MainModule {

    private MainView view;

    public MainModule(MainView view){
        this.view = view;
    }

    @Provides
    public MainView provideView(){
        return view;
    }

    @Provides
    public MainPresenter providePresenter (MainPresenterImpl impl){
        return impl;
    }

}
