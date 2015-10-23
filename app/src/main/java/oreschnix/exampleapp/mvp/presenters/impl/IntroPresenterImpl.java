package oreschnix.exampleapp.mvp.presenters.impl;

import javax.inject.Inject;

import oreschnix.exampleapp.mvp.presenters.IntroPresenter;
import oreschnix.exampleapp.mvp.views.MainView;


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
