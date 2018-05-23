package com.educationaid.tutoring.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.educationaid.tutoring.Constants.Constants;
import com.educationaid.tutoring.OfferDetailActivity;
import com.educationaid.tutoring.R;
import com.educationaid.tutoring.WebService.WebService;

import java.util.List;

public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.MyViewHolder>{

    private List<Pair<String, String>> itemList;
    private RecyclerViewClickListener mListener;
    private Activity activityView;

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView text;
        public ImageButton imgButton;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            text = view.findViewById(R.id.offerTitle);
            imgButton = view.findViewById(R.id.button_delete);
        }

        @Override
        public void onClick(final View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }


    public OfferListAdapter(Activity view, List<Pair<String, String>> itemList) {
        this.itemList = itemList;
        this.activityView = view;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.textview_offer_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pair<String, String> item = itemList.get(position);
        holder.text.setText(item.second);

        holder.imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog(activityView.findViewById(R.id.tutor_home_screen), item.first, position);
            }
        });
        holder.text.setOnClickListener(v -> displayOffer(activityView.findViewById(R.id.tutor_home_screen), item.first));
    }

    private void displayOffer(View v, String offerId) {
        Intent intent = new Intent(v.getContext(), OfferDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("offerid", Integer.parseInt(offerId));
        bundle.putInt("view", v.getId());
        intent.putExtras(bundle);
        v.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    private void showConfirmationDialog(View v, String id, int position) {
        new AlertDialog.Builder(v.getContext())
                .setTitle("Confirmation")
                .setMessage("Do you really want to delete this item?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton)
                        -> {
                    deleteOffer(id, position, v, this);

                })
                .setNegativeButton(android.R.string.no, null).show();
    }


    public void deleteOffer(String id, int position, View v, OfferListAdapter adapter) {

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                return new WebService().DeleteOffer(params[0]);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if (result.toString().equals(Constants.ANS_DELETE_OFFER)) {
                        itemList.remove(position);
                        Toast.makeText(v.getContext(), "Item deleted.", Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(v.getContext(), "Error: Item not deleted!", Toast.LENGTH_LONG).show();
                    }

            }
        }.execute(id);
    }
}