package com.educationaid.tutoring.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.educationaid.tutoring.R;

import java.util.List;

public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.MyViewHolder>{

    private List<String> itemList;
    private RecyclerViewClickListener mListener;

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView text;


        public MyViewHolder(View view, RecyclerViewClickListener listener) {
            super(view);
            view.setOnClickListener(this);
            mListener = listener;
            text = view.findViewById(R.id.offerTitle);
        }

        @Override
        public void onClick(final View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }


    public OfferListAdapter(List<String> itemList, RecyclerViewClickListener listener) {
        this.itemList = itemList;
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.textview_offer_item, parent, false);
        return new MyViewHolder(itemView, mListener);
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