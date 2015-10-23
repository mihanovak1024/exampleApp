package oreschnix.exampleapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import co.infinum.androidgoodies.ConnectionUtils;
import oreschnix.exampleapp.R;
import oreschnix.exampleapp.adapters.RestaurantsAdapter;
import oreschnix.exampleapp.dagger.components.DaggerMainComponent;
import oreschnix.exampleapp.dagger.modules.MainModule;
import oreschnix.exampleapp.models.Restaurant;
import oreschnix.exampleapp.mvp.presenters.MainPresenter;
import oreschnix.exampleapp.mvp.views.MainView;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter presenter;

    @InjectView(R.id.loading_dialog)
    ImageView dialog;

    @InjectView(R.id.list_view)
    ListView listView;

    @InjectView(R.id.refresh_button)
    TextView refresh;

    private AlphaAnimation showDialog;
    private AlphaAnimation hideDialog;

    private RestaurantsAdapter adapter;

    private boolean stopAnimation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        presenter.init();
    }

    @Override
    protected void initDependencies() {
        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().init(this);
    }


    @Override
    public void initUI() {
        setActionBarTitle(getString(R.string.restaurants));
        showDialog = new AlphaAnimation(0, 1);
        showDialog.setInterpolator(new AccelerateDecelerateInterpolator());
        showDialog.setDuration(700);
        hideDialog = new AlphaAnimation(1, 0);
        hideDialog.setInterpolator(new AccelerateDecelerateInterpolator());
        hideDialog.setDuration(700);
        showLoading();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant restaurant = adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
                intent.putExtra(RestaurantActivity.EXTRA_RESTAURANT, restaurant);
                startActivity(intent);
            }
        });
    }

    private void showLoading() {
        stopAnimation = false;
        dialog.setVisibility(View.VISIBLE);
        showDialog.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!isFinishing()) {
                    if (!stopAnimation) {
                        dialog.startAnimation(hideDialog);
                    } else {
                        dialog.clearAnimation();
                    }
                }
                }

                @Override
                public void onAnimationRepeat (Animation animation){

                }
            }

            );
            showDialog.setFillAfter(true);
            hideDialog.setAnimationListener(new Animation.AnimationListener()

            {
                @Override
                public void onAnimationStart (Animation animation){

            }

                @Override
                public void onAnimationEnd (Animation animation){

                        if (!isFinishing()) {
                            if (!stopAnimation) {
                                dialog.startAnimation(showDialog);
                            } else {
                                dialog.clearAnimation();
                            }
                        }
            }

                @Override
                public void onAnimationRepeat (Animation animation){

            }
            }

            );
            hideDialog.setFillAfter(true);
            dialog.startAnimation(hideDialog);
        }

    private void hideLoadingDialog() {
        stopAnimation = true;
        dialog.clearAnimation();
        dialog.setVisibility(View.GONE);
    }

    @Override
    public void showRestaurants(ArrayList<Restaurant> restaurants) {
        if (isFinishing()) {
            return;
        }
        hideLoadingDialog();
        adapter = new RestaurantsAdapter(MainActivity.this, restaurants);
        listView.setAdapter(adapter);


    }

    @Override
    public void showNoConnectionMessage() {
        Toast.makeText(this, getString(R.string.no_connection_message), Toast.LENGTH_LONG).show();
        hideLoadingDialog();
        stopAnimation = true;
        AlphaAnimation alphaAnim = new AlphaAnimation(0, 1);
        alphaAnim.setDuration(1000);
        refresh.startAnimation(alphaAnim);
        refresh.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        hideLoadingDialog();
    }

    @OnClick(R.id.refresh_button)
    void onRefreshClick() {
        if (ConnectionUtils.hasNetworkConnection(this)) {
            AlphaAnimation alphaAnim = new AlphaAnimation(1, 0);
            alphaAnim.setDuration(1000);
            alphaAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    refresh.setVisibility(View.GONE);
                    presenter.loadRestaurants();
                    showLoading();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            refresh.startAnimation(alphaAnim);
        } else {
            Toast.makeText(this, getString(R.string.no_connection_message), Toast.LENGTH_LONG).show();
        }
    }


}
