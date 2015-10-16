package oreschnix.exampleapp.mvp.presenters.impl;

import javax.inject.Inject;

import oreschnix.partyapp.mvp.presenters.IntroPresenter;
import oreschnix.partyapp.mvp.views.MainView;

/**
 * Created by Miha on 3.9.2015.
 */
public class IntroPresenterImpl implements IntroPresenter {

    private MainView view;

    @Inject
    public IntroPresenterImpl(MainView view) {
        this.view = view;
    }
}
