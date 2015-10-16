package oreschnix.exampleapp.mvp.presenters.impl;

import javax.inject.Inject;

import oreschnix.partyapp.mvp.presenters.MainPresenter;
import oreschnix.partyapp.mvp.views.MainView;

/**
 * Created by Miha on 2.9.2015.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView view;

    @Inject
    public MainPresenterImpl(MainView view){
        this.view = view;
    }

}
