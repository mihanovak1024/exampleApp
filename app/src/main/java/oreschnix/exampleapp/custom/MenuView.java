package oreschnix.exampleapp.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import oreschnix.exampleapp.R;
import oreschnix.exampleapp.utils.DataUtils;

/**
 * Created by Miha on 20.10.2015.
 */
public class MenuView extends LinearLayout {

    @InjectView(R.id.ll_content_view)
    LinearLayout content;

    private ArrayList<ArrayList<String>> dishes;

    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.menu_view, this);
        ButterKnife.inject(this, this);


    }

    public void setData(ArrayList<ArrayList<String>> dishes) {
        this.dishes = dishes;
        renderItems();
    }

    public void renderItems() {
        content.removeAllViews();
        LinearLayout rowLayout = null;
        LinearLayout.LayoutParams layoutParams = null;
        for (int i = 0; i < dishes.size(); i++) {
            rowLayout = new LinearLayout(getContext());
            rowLayout.setOrientation(HORIZONTAL);
            layoutParams = new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, (int) DataUtils
                    .convertDpToPixel(getResources().getDimension(R.dimen.restaurant_text_view_item_margin_top), getContext()), 0, 0);
            rowLayout.setLayoutParams(layoutParams);
            content.addView(rowLayout);

            LinearLayout menuLayout = (LinearLayout) LayoutInflater.from(getContext())
                    .inflate(R.layout.menu_view_item, this, false);
            layoutParams = new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1.0f);
            menuLayout.setLayoutParams(layoutParams);
            TextView textMenu = (TextView) menuLayout.findViewById(R.id.tv_dish);
            textMenu.setText(String.format("Menu %d:", i + 1));
            rowLayout.addView(menuLayout);

            LinearLayout columnLayout = new LinearLayout(getContext());
            columnLayout.setOrientation(VERTICAL);
            columnLayout.setLayoutParams(layoutParams);
            columnLayout.setBackground(getResources().getDrawable(R.drawable.restaurant_text_view_background));
            for (int j = 0; j < dishes.get(i).size(); j++) {
                LinearLayout itemLayout = (LinearLayout) LayoutInflater.from(getContext())
                        .inflate(R.layout.menu_view_item, this, false);
                TextView text = (TextView) itemLayout.findViewById(R.id.tv_dish);
                text.setText(dishes.get(i).get(j));
                columnLayout.addView(itemLayout);
            }
            rowLayout.addView(columnLayout);
        }
    }


}
