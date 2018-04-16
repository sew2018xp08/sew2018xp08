package com.educationaid.tutoring.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.educationaid.tutoring.R;

import java.util.List;

public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.MyViewHolder> {

    private List<String> itemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public MyViewHolder(View view) {
            super(view);
             text = view.findViewById(R.id.offerTitle);
        }
    }


    public OfferListAdapter(List<String> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.textview_offer_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String a = itemList.get(position);
        holder.text.setText(a);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}