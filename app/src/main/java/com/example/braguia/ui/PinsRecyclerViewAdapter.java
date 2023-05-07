package com.example.braguia.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.braguia.R;
import com.example.braguia.model.trails.EdgeTip;

import java.util.List;

public class PinsRecyclerViewAdapter extends RecyclerView.Adapter<PinsRecyclerViewAdapter.ViewHolder> {

    private final List<EdgeTip> mValues;
    private OnItemClickListener listener;

    public PinsRecyclerViewAdapter(List<EdgeTip> items) {
        mValues = items;
    }

    public interface OnItemClickListener {
        void onItemClick(EdgeTip ed);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pin_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.pinName.setText(mValues.get(position).getPin_name());
        holder.pinImage.setImageResource(R.drawable.uminho_logo); //TODO: isto é provisorio
        //Picasso.get().load(mValues.get(position).getPin_desc().replace("http", "https")).into(holder.imageView);

        // Set click listener for each item
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(mValues.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView pinName;

        public final ImageView pinImage;
        public EdgeTip mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            pinName = view.findViewById(R.id.pin_name);
            pinImage = view.findViewById(R.id.pin_image);
        }

        @Override
        public String toString() {
            return super.toString() + pinName;
        }
    }
}
