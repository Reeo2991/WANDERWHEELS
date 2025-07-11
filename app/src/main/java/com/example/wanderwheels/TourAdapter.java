package com.example.wanderwheels;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {

    private final List<Tour> tourList;

    public TourAdapter(List<Tour> tours) {
        this.tourList = tours;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour, parent, false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        Tour tour = tourList.get(position);
        holder.bind(tour);
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    public static class TourViewHolder extends RecyclerView.ViewHolder {
        private final ImageView tourImage;
        private final TextView tourName;
        private final TextView tourDuration;
        private final TextView tourDescription;
        private final LinearLayout tagContainer;
        private final TextView tourStartLocation;
        private final TextView tourDistance;
        private final Button viewTourButton;

        public TourViewHolder(@NonNull View itemView) {
            super(itemView);

            tourImage = itemView.findViewById(R.id.tourImage);
            tourName = itemView.findViewById(R.id.tourName);
            tourDuration = itemView.findViewById(R.id.tourDuration);
            tourDescription = itemView.findViewById(R.id.tourDescription);
            tagContainer = itemView.findViewById(R.id.tagContainer);
            tourStartLocation = itemView.findViewById(R.id.tourStartLocation);
            tourDistance = itemView.findViewById(R.id.tourDistance);
            viewTourButton = itemView.findViewById(R.id.viewTourButton);
        }
        private TextView createTagView(Context context, String tagText) {
            TextView tagView = new TextView(context);
            tagView.setText(tagText);
            tagView.setBackgroundResource(R.drawable.tag_background_small);
            tagView.setPadding(16, 8, 16, 8);
            tagView.setTextSize(12);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 0, 16, 0);
            tagView.setLayoutParams(layoutParams);

            return tagView;
        }


        public void bind(Tour tour) {
            Context context = itemView.getContext();

            // Set tour image
            tourImage.setImageResource(tour.getImageResId());

            // Set tour details
            tourName.setText(tour.getName());
            tourDuration.setText(tour.getDuration());
            tourDescription.setText(tour.getDescription());
            tourStartLocation.setText(tour.getStartLocation());
            tourDistance.setText(tour.getDistance());

            // Handle "View Tour" button click
            viewTourButton.setOnClickListener(v -> {
                Intent intent = new Intent(context, BookingActivity.class);
                intent.putExtra("tourName", tour.getName());
                intent.putExtra("tourDuration", tour.getDuration());
                intent.putExtra("tourStartLocation", tour.getStartLocation());
                intent.putExtra("tourDistance", tour.getDistance());
                intent.putExtra("tourDescription", tour.getDescription());
                intent.putExtra("tourImageResId", tour.getImageResId());
                context.startActivity(intent);

                Toast.makeText(context,
                        "Viewing details for " + tour.getName() + " tour",
                        Toast.LENGTH_SHORT).show();
            });


        }

    }
}