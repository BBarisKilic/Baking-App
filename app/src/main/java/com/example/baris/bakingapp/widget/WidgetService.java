package com.example.baris.bakingapp.widget;

import android.content.Intent;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import java.util.ArrayList;

import com.example.baris.bakingapp.R;
import com.example.baris.bakingapp.helper.RecipeData;
import com.example.baris.bakingapp.model.Ingredient;
import com.example.baris.bakingapp.model.RP;

public class WidgetService extends RemoteViewsService
{
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new widgetRemoteViewsFactory(this.getApplicationContext());
    }

    public class widgetRemoteViewsFactory implements RemoteViewsFactory {

        Context context;
        ArrayList<Ingredient> ingredients;
        Ingredient ingredient;
        RP rp = RecipeData.recipe;

        widgetRemoteViewsFactory(Context context) {
            this.context = context;
        }

        private void setIngredientsData(){
            rp = RecipeData.recipe;
            if(rp != null){
                ingredients = RecipeData.recipe.getIngredients();
            } else{
                ingredients = new ArrayList<>();
            }
        }

        @Override
        public void onCreate() {
            setIngredientsData();
        }

        @Override
        public void onDestroy() {
        }

        @Override
        public void onDataSetChanged() {
            setIngredientsData();
        }

        @Override
        public int getCount() {
            if(ingredients == null)
                return 0;
            else
                return ingredients.size();
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public RemoteViews getViewAt(int pos) {
            ingredient = ingredients.get(pos);
            RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.widget_items);
            remoteView.setTextViewText(R.id.widget_qty, String.valueOf(ingredient.getQty()));
            remoteView.setTextViewText(R.id.widget_measure, ingredient.getMeasure());
            remoteView.setTextViewText(R.id.widget_ingredient, ingredient.getIngredient());
            return remoteView;
        }
    }
}
