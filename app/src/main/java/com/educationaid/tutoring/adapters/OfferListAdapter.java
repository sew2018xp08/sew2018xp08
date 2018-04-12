package com.educationaid.tutoring.adapters;
import android.view.View;
import android.view.ViewGroup;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.educationaid.tutoring.R;

// here's our beautiful adapter
public class OfferListAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private int layoutResourceId;
    private String data[] = null;

    public OfferListAdapter(Context mContext, int layoutResourceId, String[] data) {

        super(mContext, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // object item based on the position
        String objectItem = data[position];

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView textViewItem = convertView.findViewById(R.id.offerItem);
        textViewItem.setText(objectItem);

        return convertView;

    }

}