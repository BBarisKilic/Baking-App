package com.example.baris.bakingapp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import com.example.baris.bakingapp.adapter.StepAdapter;
import com.example.baris.bakingapp.model.Ingredient;
import com.example.baris.bakingapp.model.Step;

public class DetailsFragment extends Fragment
{
    private String recipeName;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Step> steps;
    private OnStepListItemSelected onStepListItemSelected;
    RecyclerView stepsView;

    public DetailsFragment() {
    }

    public interface OnStepListItemSelected {
        void onStepListItemSelected(int pos);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onStepListItemSelected = (OnStepListItemSelected) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        TextView recipeNameView = (TextView) rootView.findViewById(R.id.chosen_recipeName_tv);
        TextView ingredientsView = (TextView) rootView.findViewById(R.id.ingredients_tv);
        stepsView = (RecyclerView) rootView.findViewById(R.id.short_descriptions_rv);

        if (savedInstanceState != null) {
            ingredients = savedInstanceState.getParcelableArrayList("INGREDIENTS_SAVED");
            steps = savedInstanceState.getParcelableArrayList("STEPS_SAVED");
            recipeName = savedInstanceState.getString("RECIPE_NAME_SAVED");

        } else {
            if (getArguments() != null) {
                ingredients = getArguments().getParcelableArrayList("INGREDIENTS");
                steps = getArguments().getParcelableArrayList("STEPS");
                recipeName = getArguments().getString("RECIPE_NAME");
            }
        }

        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        stepsView.setLayoutManager(layoutmanager);

        StepAdapter stepAdapter = new StepAdapter(steps, new StepAdapter.OnStepListItemClickListener() {
            @Override
            public void onStepListItemClick(Step item) {
                onStepListItemSelected.onStepListItemSelected(item.getID());
            }
        });

        recipeNameView.setText(recipeName);

        for (int a = 0; a < ingredients.size(); a++) {
            Ingredient ingredient = (Ingredient) ingredients.get(a);
            ingredientsView.append(String.valueOf(ingredient.getQty()));
            ingredientsView.append("  " + ingredient.getMeasure() + " x ");
            ingredientsView.append(ingredient.getIngredient() + "\n");
        }

        stepsView.setAdapter(stepAdapter);

        return rootView;
    }
    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putParcelableArrayList("INGREDIENTS_SAVED", ingredients);
        currentState.putParcelableArrayList("STEPS_SAVED", steps);
        currentState.putString("RECIPE_NAME_SAVED", recipeName);
    }
}