package com.samkecy.nationalmuseum;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CustomAdapter extends BaseAdapter {
    String namy;
    TextView name;
    String values;

    Context context;
    // use array lists instead
    int[] images;
    // use array lists instead
    String[] names;
    LayoutInflater layoutInflater;

    CustomAdapter(Context applicationContext, String[] names, int[] images) {

        this.context = applicationContext;
        this.images = images;
        this.names = names;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //layoutInflater = (LayoutInflater.from(applicationContext));

    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        // return the position
        // of the item in the adapter
        return names[i];
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_items, null);

            // get references to the texts and images
            name = view.findViewById(R.id.tvArtName);
            ImageView image = view.findViewById(R.id.imgArt);

            // set the values
            name.setText(names[position]);
            image.setImageResource(images[position]);
            values = name.getText().toString();
        }




        return view;
    }


}