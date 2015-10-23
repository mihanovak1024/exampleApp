package oreschnix.exampleapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import oreschnix.exampleapp.R;
import oreschnix.exampleapp.dagger.components.DaggerIntroComponent;
import oreschnix.exampleapp.dagger.modules.IntroModule;
import oreschnix.exampleapp.mvp.views.IntroView;

/**
 * Created by Miha on 2.9.2015.
 */
public class IntroActivity extends BaseActivity implements IntroView {

    @InjectView(R.id.iv_background)
    ImageView background;
    @InjectView(R.id.ll_layout)
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.inject(this);
        initUI();
    }

    @Override
    public void initUI() {
        AlphaAnimation backgroundAnimation = new AlphaAnimation(0, 1);
        backgroundAnimation.setDuration(1000);
        final Animation introButtonAnimation = AnimationUtils.loadAnimation(this, R.anim.intro_button_appear);
        backgroundAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                introButtonAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                layout.startAnimation(introButtonAnimation);
                layout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        background.startAnimation(backgroundAnimation);
        background.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.button)
    void onButtonTwoClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void initDependencies() {
        DaggerIntroComponent.builder().introModule(new IntroModule(this)).build().init(this);
    }


}
