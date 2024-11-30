package com.example.imageapp.network;

import com.example.imageapp.models.ImageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/images/")
    Call<List<ImageModel>> getImages();
}

