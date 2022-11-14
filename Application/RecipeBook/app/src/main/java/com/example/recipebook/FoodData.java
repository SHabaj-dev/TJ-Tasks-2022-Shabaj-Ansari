package com.example.recipebook;

import java.util.List;

public class FoodData {

    private String itemName;
    private String timeTaken;
    private List<String> ingredients;
    private int image;

    public FoodData(String itemName, String timeTaken, List<String> ingredients, int image) {
        this.itemName = itemName;
        this.timeTaken = timeTaken;
        this.ingredients = ingredients;
        this.image = image;
    }

    public String getItemName() {
        return itemName;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getImage() {
        return image;
    }

    public void setImageURL(int imageURL) {
        this.image = imageURL;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
