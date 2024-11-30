package com.example.imageapp.adapters;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.imageapp.R;
import com.example.imageapp.models.ImageModel;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private final List<ImageModel> images;
    private final OnImageClickListener listener;

    public ImageAdapter(List<ImageModel> images, OnImageClickListener listener) {
        this.images = images;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageModel image = images.get(position);
        holder.nameTextView.setText(image.getName());

        // Load image with Glide and handle placeholders and errors
        Glide.with(holder.imageView.getContext())
                .load(image.getUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error_placeholder)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("GLIDE_ERROR", "Image load failed: " + e.getMessage(), e);
                        return false; // Allow Glide to handle the error placeholder
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.d("GLIDE_SUCCESS", "Image loaded successfully");
                        return false;
                    }
                })
                .into(holder.imageView);

        holder.itemView.setOnClickListener(view -> listener.onImageClick(image));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public interface OnImageClickListener {
        void onImageClick(ImageModel image);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView nameTextView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }
    }
}
