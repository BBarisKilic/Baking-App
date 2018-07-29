package com.example.baris.bakingapp;

import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;

import com.example.baris.bakingapp.adapter.RecipeAdapter;
import com.example.baris.bakingapp.helper.RecipeData;
import com.example.baris.bakingapp.helper.idlingResource;
import com.example.baris.bakingapp.helper.Utils;
import com.example.baris.bakingapp.model.RP;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.OnItemClickListener
{
    private RecyclerView recipes_rv;
    private ProgressBar progressBar;
    private static final String TAG = "MainActivity";

    @Nullable
    private idlingResource idlingResource;

    @VisibleForTesting
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new idlingResource();
        }
        return idlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView emptyStateTextView = findViewById(R.id.error_connection_tv);
        Toolbar toolbar = findViewById(R.id.recipeToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }
        recipes_rv = findViewById(R.id.recipes_rv);
        progressBar = findViewById(R.id.progressBar);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        int spanCount;

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            spanCount = 1;
        } else {
            spanCount = 2;
        }

        if (Utils.isConnected(this))
        {
            emptyStateTextView.setVisibility(View.GONE);
            recipes_rv.setVisibility(View.VISIBLE);
            GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
            recipes_rv.setLayoutManager(layoutManager);
            Utils.loadRecipe(this);

        } else {
            View progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);
            emptyStateTextView.setVisibility(View.VISIBLE);
            emptyStateTextView.setText(R.string.no_internet_connection);
        }

        getIdlingResource();
    }

    public void setRecipeList(ArrayList<RP> rps) {
        RecipeAdapter recipeAdapter = new RecipeAdapter(rps);
        recipeAdapter.setRecipes(rps);
        recipeAdapter.setOnItemClickListener(this);
        recipes_rv.setAdapter(recipeAdapter);
    }

    @Override
    public void onItemClick(int pos, RP rp, ImageView imageView) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        RecipeData.recipe = rp;
        startActivity(intent);
    }

    public void setProgressBarVisibility(int i) {
        progressBar.setVisibility(View.GONE);
    }
}
