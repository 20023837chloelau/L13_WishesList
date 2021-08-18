package sg.edu.rp.c346.id20023837.wisheslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<item> item;

    public CustomAdapter(Context context, int resource, ArrayList<item> objects) {
        super(context, resource, objects);
        this.parent_context = context;
        this.layout_id = resource;
        this.item = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.tvName);
        TextView tvLocation = rowView.findViewById(R.id.tvLocation);
        TextView tvPrice = rowView.findViewById(R.id.tvPrice);
        RatingBar ratingBar = rowView.findViewById(R.id.ratingBar);
        ImageView imageView = rowView.findViewById(R.id.imageView);

        item currentItem = item.get(position);

        tvTitle.setText(currentItem.getName());
        tvLocation.setText(currentItem.getLocation());
        String stars = "";
        for(int i = 0; i < currentItem.getStars(); i++){
            stars += " * ";
        }

        ratingBar.setRating(currentItem.getStars());

        tvPrice.setText(currentItem.getPrice() + "");

        if (currentItem.getStars() > 4) {
            imageView.setImageResource(R.drawable.musthaveicon);
        } else {
            imageView.setVisibility(View.GONE);
        }

        return rowView;
    }

}
