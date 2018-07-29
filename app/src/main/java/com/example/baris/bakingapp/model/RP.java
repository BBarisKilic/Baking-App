package com.example.baris.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class RP implements Parcelable
{
    @SerializedName("id")
    private int ID;
    @SerializedName("name")
    private String recipeName;
    @SerializedName("servings")
    private int servings;
    @SerializedName("ingredients")
    private ArrayList<Ingredient> ingredients;
    @SerializedName("steps")
    private ArrayList<Step> steps;
    @SerializedName("image")
    private String img;

    public RP() {
    }

    public RP(int ID, String recipeName, ArrayList<Ingredient> ingredients, ArrayList<Step> steps, int servings, String img) {
        this.ID = ID;
        this.recipeName = recipeName;
        this.servings = servings;
        this.ingredients = ingredients;
        this.steps = steps;
        this.img = img;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getServings() {
        return servings;
    }
    public void setServings(int servings) {
        this.servings = servings;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private RP(Parcel parcel) {
        this.ID = parcel.readInt();
        this.recipeName = parcel.readString();
        this.servings = parcel.readInt();
        this.ingredients = new ArrayList<>();
        parcel.readArrayList(Ingredient.class.getClassLoader());
        this.steps = new ArrayList<>();
        parcel.readArrayList(Step.class.getClassLoader());
        this.img = parcel.readString();
    }

    public static final Parcelable.Creator<RP> CREATOR = new Parcelable.Creator<RP>() {
        public RP[] newArray(int size) {
            return new RP[size];
        }
        public RP createFromParcel(Parcel parcel) {
            return new RP(parcel);
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(ID);
        parcel.writeString(recipeName);
        parcel.writeList(ingredients);
        parcel.writeList(steps);
        parcel.writeInt(servings);
        parcel.writeString(img);
    }
}
