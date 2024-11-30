package com.example.imageapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imageapp.adapters.ImageAdapter;
import com.example.imageapp.models.ImageModel;
import com.example.imageapp.network.ApiClient;
import com.example.imageapp.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchImages();
    }

    private void fetchImages() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        apiService.getImages().enqueue(new Callback<List<ImageModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<ImageModel>> call, @NonNull Response<List<ImageModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ImageModel> images = response.body();
                    for (ImageModel image : images) {
                        Log.d("IMAGE_URL", "URL: " + image.getUrl());
                    }
                    // Set up RecyclerView adapter
                    ImageAdapter adapter = new ImageAdapter(images, image -> {
                        Intent intent = new Intent(ImageListActivity.this, ImageDetailActivity.class);
                        intent.putExtra("image_url", image.getUrl());
                        intent.putExtra("image_name", image.getName());
                        startActivity(intent);
                    });
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("API_ERROR", "Response Code: " + response.code());
                    Log.e("API_ERROR", "Response Message: " + response.message());
                    Toast.makeText(ImageListActivity.this, "No images available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ImageModel>> call, @NonNull Throwable t) {
                Log.e("API_FAILURE", "Error: " + t.getMessage());
                Toast.makeText(ImageListActivity.this, "Failed to fetch images", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
