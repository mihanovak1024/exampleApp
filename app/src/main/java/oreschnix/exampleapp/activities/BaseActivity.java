package oreschnix.exampleapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.InjectView;
import butterknife.Optional;
import oreschnix.exampleapp.R;
import oreschnix.exampleapp.mvp.views.BaseView;

/**
 * Created by Miha on 2.9.2015.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_disapear_to_left, R.anim.activity_apear_from_right);
        initDependencies();
    }

    @Optional
    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            finish();
            finishActivityAnimation();
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract void initDependencies();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        finishActivityAnimation();
    }

    protected void setActionBarTitle(String text) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(text);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_back_gray);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                finishActivityAnimation();
            }
        });
    }

    @Override
    public void finishActivityAnimation() {
        overridePendingTransition(R.anim.activity_apear_from_left, R.anim.activity_disapear_to_right);
    }
}
