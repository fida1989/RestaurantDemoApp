package com.hungrydroid.restaurantapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hungrydroid.restaurantapp.MenuListActivity;
import com.hungrydroid.restaurantapp.R;
import com.squareup.picasso.Picasso;

public class MenuAdapter extends BaseAdapter {
    String[] result;
    Context context;
    String[] imageId;
    private static LayoutInflater inflater = null;
    String[] newsId;

    public MenuAdapter(Activity mainActivity, String[] prgmNameList, String[] prgmImages, String[] Id) {
        // TODO Auto-generated constructor stub
        result = prgmNameList;
        context = mainActivity;
        imageId = prgmImages;
        newsId = Id;

        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        //final Type user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        Holder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_menu_row1, parent, false);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.textView1);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.imageView1);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (Holder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.

        //viewHolder.image.setText(user.image);


        viewHolder.tv.setText(result[position]);
        Picasso.with(context).load(imageId[position]).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher).into(viewHolder.img);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //DataWrapper.quotes = user.quotes;
                //Toast.makeText(context, "You Clicked " + position, Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, "You Clicked " + position, Toast.LENGTH_LONG).show();
                Intent i = new Intent(context, MenuListActivity.class);
               // i.putExtra("name", user.name);
                //i.putExtra("image", user.image);
                //i.putExtra("quotes", user.quotes);
                //i.putExtra("quotes", new DataWrapper(user.quotes));
               context.startActivity(i);

            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
}