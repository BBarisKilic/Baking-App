package com.example.baris.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Toast;

import com.example.baris.bakingapp.helper.RecipeData;
import com.example.baris.bakingapp.model.RP;
import com.example.baris.bakingapp.widget.WidgetProvider;


public class DetailsActivity extends AppCompatActivity implements DetailsFragment.OnStepListItemSelected {

    private RP selectedRecipe;
    private boolean isTwoPane;
    public Bundle bundle = new Bundle();
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        FloatingActionButton fabutton = findViewById(R.id.fabutton);
        Toolbar toolbar = findViewById(R.id.recipeToolbar);

        fabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenUpdt = new Intent(DetailsActivity.this, WidgetProvider.class);
                intenUpdt.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                int[] ids = AppWidgetManager.getInstance(getApplication())
                        .getAppWidgetIds(new ComponentName(getApplication(), WidgetProvider.class));
                intenUpdt.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
                sendBroadcast(intenUpdt);
                context = v.getContext();
                Toast.makeText(context,"Updated widget with new ingredients!", Toast.LENGTH_LONG).show();
            }
        });

        if (savedInstanceState != null) {
            isTwoPane = savedInstanceState.getBoolean("PANE");
            selectedRecipe = savedInstanceState.getParcelable("RECIPE");
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(selectedRecipe.getRecipeName());
            }
            return;
        }

        selectedRecipe = RecipeData.recipe;
        if (selectedRecipe != null) {
            bundle.putParcelableArrayList("INGREDIENTS", selectedRecipe.getIngredients());
            bundle.putParcelableArrayList("STEPS", selectedRecipe.getSteps());
            bundle.putString("RECIPE_NAME", selectedRecipe.getRecipeName());
        }

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(selectedRecipe.getRecipeName());
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTwoPane){
                    onBackPressed();
                } else {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    if (!isTwoPane && findViewById(R.id.step_frame) != null) {
                        fragmentManager.popBackStack();
                    } else {
                        finish();
                        Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                        RecipeData.recipe = null;
                        startActivity(intent);
                    }
                }
            }
        });

        if (findViewById((R.id.twoPane)) != null)
        {
            isTwoPane = true;

            if (savedInstanceState == null) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                StepFragment stepFragment = new StepFragment();
                stepFragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.step_fragment, stepFragment)
                        .commit();

                DetailsFragment detailsFragment = new DetailsFragment();
                detailsFragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.details_fragment, detailsFragment)
                        .addToBackStack(null)
                        .commit();
            }
        } else {
            isTwoPane = false;

            FragmentManager fragmentManager = getSupportFragmentManager();
            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.details_fragment, detailsFragment)
                    .commit();
        }
    }

    @Override
    public void onStepListItemSelected(int pos) {
        RecipeData.index = pos;
        selectedRecipe = RecipeData.recipe;

        if (!isTwoPane) {
            StepFragment stepFragment = new StepFragment();
            stepFragment.setListIndex(pos);
            bundle.putParcelableArrayList("STEPS", selectedRecipe.getSteps());
            stepFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_frame, stepFragment)
                    .addToBackStack("STEP_STACK")
                    .commit();
        } else {
            StepFragment stepFragment = new StepFragment();
            stepFragment.setListIndex(pos);
            bundle.putParcelableArrayList("STEPS", selectedRecipe.getSteps());
            stepFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_fragment, stepFragment)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        RecipeData.index = 0;
        if (isTwoPane) {
            finish();
            super.onBackPressed();
        } else if (findViewById(R.id.step_frame) != null) {
            getSupportFragmentManager().popBackStack();
        } else if (findViewById(R.id.details_frame) != null) {
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            RecipeData.recipe = null;
            startActivity(intent);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("PANE", isTwoPane);
        bundle.putParcelable("RECIPE", selectedRecipe);
    }
}