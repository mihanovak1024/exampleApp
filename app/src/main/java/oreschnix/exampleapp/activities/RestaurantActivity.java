package oreschnix.exampleapp.activities;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import oreschnix.exampleapp.R;
import oreschnix.exampleapp.custom.MenuView;
import oreschnix.exampleapp.models.Restaurant;

/**
 * Created by Miha on 19.10.2015.
 */
public class RestaurantActivity extends BaseActivity {

    public static final String EXTRA_RESTAURANT = "EXTRA_RESTAURANT";

    @InjectView(R.id.mv_dishes)
    MenuView dishes;
    @InjectView(R.id.tv_restaurant_address)
    TextView tvAddress;
    @InjectView(R.id.tv_restaurant_telephone)
    TextView tvTelephone;
    @InjectView(R.id.restaurant_price)
    TextView tvPrice;
    @InjectView(R.id.restaurant_saturday_open)
    TextView saturday;
    @InjectView(R.id.restaurant_sunday_open)
    TextView sunday;
    @InjectView(R.id.restaurant_week_open)
    TextView week;

    private Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        ButterKnife.inject(this);
        this.restaurant = (Restaurant) getIntent().getSerializableExtra(EXTRA_RESTAURANT);
        setActionBarTitle(restaurant.getName());
        dishes.setData(restaurant.getDishMenu());
        setComponents();
    }

    @Override
    protected void initDependencies() {

    }

    private void setComponents() {
        tvPrice.setText(restaurant.getPrice());
        tvAddress.setText(restaurant.getAddress());
        String telNr = "";
        if (restaurant.getTelephoneNumbers().size() > 0) {
            for (int i = 0; i < restaurant.getTelephoneNumbers().size(); i++) {
                telNr += restaurant.getTelephoneNumbers().get(i);
                if (i < restaurant.getTelephoneNumbers().size() - 1) {
                    telNr += "\n";
                }
            }
        } else {
            telNr += "No number";
        }
        tvTelephone.setText(telNr);
        week.setText(restaurant.getOpening().getWeek().get(0)
                + "\n" + restaurant.getOpening().getWeek().get(1));
        if (restaurant.getOpening().getSaturday() instanceof ArrayList) {
            saturday.setText(((ArrayList) restaurant.getOpening().getSaturday()).get(0)
                    + "\n" + ((ArrayList) restaurant.getOpening().getSaturday()).get(1));
        } else {
            saturday.setText(getString(R.string.restaurant_closed));
        }
        if (restaurant.getOpening().getSunday() instanceof ArrayList) {
            sunday.setText(((ArrayList) restaurant.getOpening().getSunday()).get(0)
                    + "\n" + ((ArrayList) restaurant.getOpening().getSunday()).get(1));
        } else {
            sunday.setText(getString(R.string.restaurant_closed));
        }
    }
}
