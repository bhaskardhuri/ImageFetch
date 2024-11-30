package com.example.imageapp.network;

import com.example.imageapp.models.ImageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("images") // Endpoint after the base URL
    Call<List<ImageModel>> getImages();
}
