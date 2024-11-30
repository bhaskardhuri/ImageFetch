package com.example.imageapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ImageDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.textView);

        String imageUrl = getIntent().getStringExtra("image_url");
        String imageName = getIntent().getStringExtra("image_name");

        if (imageUrl != null) {
            Glide.with(this).load(imageUrl).into(imageView);
        } else {
            imageView.setImageResource(R.drawable.placeholder); // Add placeholder image in drawable
        }

        textView.setText(imageName != null ? imageName : "No Name");
    }
}

