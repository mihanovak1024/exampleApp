package oreschnix.exampleapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.FacebookSdk;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import oreschnix.partyapp.R;
import oreschnix.partyapp.dagger.components.DaggerIntroComponent;
import oreschnix.partyapp.dagger.modules.IntroModule;
import oreschnix.partyapp.mvp.views.IntroView;
import oreschnix.partyapp.utilities.PreferenceUtils;

/**
 * Created by Miha on 2.9.2015.
 */
public class IntroActivity extends BaseActivity implements IntroView {

    @InjectView(R.id.button_one)
    TextView buttonOne;
    @InjectView(R.id.button_two)
    TextView buttonTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.inject(this);
        FacebookSdk.sdkInitialize(this);
    }

    @Override
    public void initUI() {

    }

    @OnClick(R.id.fl_intro_events)
    void clickEvents() {
        Intent intent;
        String accToken = PreferenceUtils.getAccessToken();
        long accTokenExp = PreferenceUtils.getAccessTokenExpires();
        if (accToken!=null && !accToken.isEmpty()
                && accTokenExp>0) {
            intent = new Intent(IntroActivity.this, EventsActivity.class);
        } else{
            intent = new Intent(IntroActivity.this, LoginActivity.class);
        }
        startActivity(intent);
    }

    @OnClick(R.id.fl_restaurants)
    void clickRestaurants() {
        Intent intent = new Intent(IntroActivity.this, RestaurantsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initDependencies() {
        DaggerIntroComponent.builder().introModule(new IntroModule(this)).build().init(this);
    }
}
