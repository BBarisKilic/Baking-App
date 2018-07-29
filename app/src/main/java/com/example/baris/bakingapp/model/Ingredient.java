package com.example.baris.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Ingredient implements Parcelable {

    @SerializedName("measure")
    private String measure;
    @SerializedName("ingredient")
    private String ingredient;
    @SerializedName("quantity")
    private Double qty;

    public Ingredient() {
    }
    public Ingredient(Double qty, String measure, String ingredient) {
        this.qty = qty;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    private Ingredient(Parcel parcel) {
        qty = parcel.readDouble();
        measure = parcel.readString();
        ingredient = parcel.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(qty);
        parcel.writeValue(measure);
        parcel.writeValue(ingredient);
    }
    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        public Ingredient createFromParcel(Parcel parcel) {
            return new Ingredient(parcel);
        }

        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}
