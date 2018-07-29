package com.example.baris.bakingapp.helper;

import android.content.Context;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.baris.bakingapp.model.RP;
import com.example.baris.bakingapp.network.API;
import com.example.baris.bakingapp.MainActivity;

public abstract class Utils
{
    public static void loadRecipe(final Activity activity) {
        API.RecipeParsing recipe = API.getRecipe();
        recipe.getRecipes().enqueue(new Callback<ArrayList<RP>>() {
            @Override
            public void onResponse(Call<ArrayList<RP>> call, Response<ArrayList<RP>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ArrayList<RP> rps = new ArrayList<>();
                    rps.addAll(response.body());
                    MainActivity recipesActivity = (MainActivity) activity;
                    recipesActivity.setRecipeList(rps);
                    recipesActivity.setProgressBarVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(@NonNull Call<ArrayList<RP>> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                RecipeData.bool = false;
            }
        });
    }
    public static boolean isConnected(Context context) {
        boolean isConnected;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = networkInfo != null && networkInfo.isConnected();
        return isConnected;
    }
}
