package com.educationaid.tutoring.adapters;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.educationaid.tutoring.R;
import com.educationaid.tutoring.TutorHomeScreenActivity;

import java.util.List;

public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.MyViewHolder>{

    private List<String> itemList;
    private RecyclerViewClickListener mListener;

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

        holder.imgButton.setOnClickListener(this::showConfirmationDialog);
        holder.text.setOnClickListener(v -> Toast.makeText(v.getContext(), "Position " + position, Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private void showConfirmationDialog(View v) {
        new AlertDialog.Builder(v.getContext())
                .setTitle("Confirmation")
                .setMessage("Do you really want to delete this item?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton)
                        -> Toast.makeText(v.getContext(), "The item was deleted.", Toast.LENGTH_SHORT).show())
                .setNegativeButton(android.R.string.no, null).show();
    }
}