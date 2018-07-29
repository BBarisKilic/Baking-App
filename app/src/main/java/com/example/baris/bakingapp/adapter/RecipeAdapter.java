package com.example.baris.bakingapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import java.util.ArrayList;
import java.util.List;

import com.example.baris.bakingapp.R;
import com.example.baris.bakingapp.model.RP;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int pos, RP rp, ImageView imageView);
    }
    public List<RP> recipes;
    private OnItemClickListener onItemClickListener;

    public RecipeAdapter(List<RP> recipes) {
        this.recipes = recipes;
    }
    @Override
    public RecipeAdapter.RecipeAdapterViewHolder onCreateViewHolder(ViewGroup vGroup, int i) {
        View v = LayoutInflater
                .from(vGroup.getContext())
                .inflate(R.layout.cv_rp, vGroup, false);

        return new RecipeAdapterViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final RecipeAdapterViewHolder holder, int pos) {
        Context context = holder.itemView.getContext();
        RP recipe = recipes.get(pos);

        String recipe_img_name = "empty";
        int resID = -1;
        int id = recipe.getID();
        switch (id) {
            case 1:
                recipe_img_name = "nutella_pie";
                resID = context.getResources().getIdentifier(recipe_img_name, "drawable", context.getPackageName());
                Picasso.with(context)
                        .load(resID)
                        .transform(new BlurTransformation(context))
                        .into(holder.recipeImage);
                break;
            case 2:
                recipe_img_name = "brownies";
                resID = context.getResources().getIdentifier(recipe_img_name, "drawable", context.getPackageName());
                Picasso.with(context)
                        .load(resID)
                        .transform(new BlurTransformation(context))
                        .into(holder.recipeImage);
                break;
            case 3:
                recipe_img_name = "yellow_cake";
                resID = context.getResources().getIdentifier(recipe_img_name, "drawable", context.getPackageName());
                Picasso.with(context)
                        .load(resID)
                        .transform(new BlurTransformation(context))
                        .into(holder.recipeImage);
                break;
            case 4:
                recipe_img_name = "cheesecake";
                resID = context.getResources().getIdentifier(recipe_img_name, "drawable", context.getPackageName());
                Picasso.with(context)
                        .load(resID)
                        .transform(new BlurTransformation(context))
                        .into(holder.recipeImage);
                break;
            default:
                holder.recipeImage.setImageDrawable(context.getResources().getDrawable(R.drawable.brownies));
                break;
        }
        holder.recipeName.setText(recipe.getRecipeName());
        holder.recipeServing.setText(String.format("%d", recipe.getServings()));
    }

    @Override
    public int getItemCount() {
        if (null == recipes) return 0;
        return recipes.size();
    }

    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        CardView cardView;
        TextView recipeName;
        TextView recipeServing;
        ImageView recipeImage;
        public RecipeAdapterViewHolder(final View item) {
            super(item);
            cardView = item.findViewById(R.id.recipe_cv);
            recipeImage = item.findViewById(R.id.rp_image_iv);
            recipeName = item.findViewById(R.id.rp_name_tv);
            recipeServing = item.findViewById(R.id.serving_size);
            item.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                int pos = getAdapterPosition();
                RP recipe = recipes.get(pos);
                onItemClickListener.onItemClick(pos, recipe, recipeImage);
            }
        }
    }
    public void setRecipes(ArrayList<RP> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(final OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
