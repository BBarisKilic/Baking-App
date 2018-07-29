package com.example.baris.bakingapp.network;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import com.example.baris.bakingapp.model.RP;

public class API {
    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    public static RecipeParsing getRecipe() {
        return RetrofitClient.getClient(BASE_URL).create(RecipeParsing.class);
    }
    public static interface RecipeParsing {
        @GET("baking.json")
        Call<ArrayList<RP>> getRecipes();
    }

    public static class RetrofitClient {
        private static Retrofit retrofit = null;
        static Retrofit getClient(String url) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }
}
