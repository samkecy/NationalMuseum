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
String  values;

    Context context;
    int[] images;
    String[] names;
    LayoutInflater layoutInflater;

    public CustomAdapter(Context applicationContext, String[] names, int[] images) {

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
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        view = layoutInflater.inflate(R.layout.list_items, null);
         name = (TextView) view.findViewById(R.id.tvArtName);
        ImageView image = (ImageView) view.findViewById(R.id.imgArt);
        name.setText(names[position]);
        image.setImageResource(images[position]);
       values = name.getText().toString();



        return view;
    }


}