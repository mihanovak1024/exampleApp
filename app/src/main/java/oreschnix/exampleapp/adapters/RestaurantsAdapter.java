package oreschnix.exampleapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import oreschnix.exampleapp.R;
import oreschnix.exampleapp.models.Restaurant;

/**
 * Created by Miha on 19.10.2015.
 */
public class RestaurantsAdapter extends ArrayAdapter<Restaurant> {

    private Context context;

    public RestaurantsAdapter(Context context, ArrayList<Restaurant> restaurants) {
        super(context, 0, restaurants);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Restaurant restaurant = getItem(position);

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.restaurant_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.name.setText(restaurant.getName());
        String openingHours = context.getString(R.string.restaurant_week);
        if (restaurant.getOpening().getWeek().size() > 0) {
            openingHours +=
                    String.format(context.getString(R.string.restaurant_opened), restaurant.getOpening().getWeek().get(0),
                            restaurant.getOpening().getWeek().get(1));
        }
        if (restaurant.getOpening().getSaturday() instanceof ArrayList) {
            openingHours += "\n" + context.getString(R.string.restaurant_saturday)
                    + String.format(context.getString(R.string.restaurant_opened), ((ArrayList) restaurant.getOpening().getSaturday()).get(0),
                    ((ArrayList) restaurant.getOpening().getSaturday()).get(1));
        } else {
            openingHours += "\n" + context.getString(R.string.restaurant_saturday)
                    + context.getString(R.string.restaurant_closed);
        }
        if (restaurant.getOpening().getSunday() instanceof ArrayList) {
            openingHours += "\n" + context.getString(R.string.restaurant_sunday)
                    + String.format(context.getString(R.string.restaurant_opened), ((ArrayList) restaurant.getOpening().getSunday()).get(0),
                    ((ArrayList) restaurant.getOpening().getSunday()).get(1));
        } else {
            openingHours += "\n" + context.getString(R.string.restaurant_sunday)
                    + context.getString(R.string.restaurant_closed);
        }

        holder.openClose.setText(openingHours);
        holder.address.setText(context.getString(R.string.restaurant_address) + restaurant.getAddress());
        if (restaurant.getTelephoneNumbers().size() > 0) {
            String telNr = "";
            for (int i = 0; i < restaurant.getTelephoneNumbers().size(); i++) {
                telNr += restaurant.getTelephoneNumbers().get(i);
                if (i < restaurant.getTelephoneNumbers().size() - 1) {
                    telNr += "\n";
                }
            }
            holder.telephone.setText(context.getString(R.string.restaurant_telephone) + telNr);
        } else {
            holder.telephone.setText(context.getString(R.string.restaurant_telephone) + "No number");
        }
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.restaurant_name)
        TextView name;
        @InjectView(R.id.restaurant_open_close)
        TextView openClose;
        @InjectView(R.id.restaurant_address)
        TextView address;
        @InjectView(R.id.restaurant_telephone)
        TextView telephone;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
