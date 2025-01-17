package com.example.braguia.ui.viewAdapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.braguia.R;
import com.example.braguia.model.trails.Trail;
import com.example.braguia.ui.Activitys.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TrailsHomeRecyclerViewAdapter extends RecyclerView.Adapter<TrailsHomeRecyclerView> {

    private final List<Trail> data;
    private OnItemClickListener listener;

    public TrailsHomeRecyclerViewAdapter(List<Trail> items) {
        data = items;
    }

    public interface OnItemClickListener {
        void onItemClick(Trail trail);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TrailsHomeRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_trail_main_item, parent, false);

        return new TrailsHomeRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(final TrailsHomeRecyclerView holder, int position) {
        holder.mItem = data.get(position);
        holder.trailName.setText(data.get(position).getTrail_name());
        Picasso.get().load(data.get(position)
                        .getTrail_img().replace("http", "https"))
                .error(R.drawable.no_preview_image)
                .into(holder.imageView);

        // Set text color based on theme mode
        int textColor, cardColor;
        MainActivity mainActivity = (MainActivity) holder.itemView.getContext();
        if (mainActivity.isDarkModeEnabled()) {
            textColor = Color.WHITE;
            cardColor = Color.GRAY;
        } else {
            textColor = Color.BLACK;
            cardColor = Color.WHITE;
        }
        holder.trailName.setTextColor(textColor);
        CardView cd = holder.mView.findViewById(R.id.card_trail_main_view);
        cd.setCardBackgroundColor(cardColor);
        // Set click listener for each item
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(data.get(position));
            }

        });
    }

    @Override
    public int getItemCount() {return data.size();}


}
