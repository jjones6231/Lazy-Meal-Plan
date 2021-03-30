package space.kingshiba1;

import java.util.Arrays;

public class Meal implements Comparable<Meal>{
    private int calorie;
    private int protein;
    private Ingredient[] ingredients;
    private boolean leftovers;
    private String name;
    public boolean recentlyEaten;

    public Meal(String name, int cal, int pro, Ingredient[] ing, boolean left){
        this.name = name;
        calorie = cal;
        protein = pro;
        ingredients = ing;
        leftovers = left;
        recentlyEaten = false;
    }

    public String getMealName(){
        return name;
    }

    public int getCal(){
        return calorie;
    }

    public Ingredient[] getIngredients(){
        return ingredients;
    }


    public int getProtein(){
        return protein;
    }

    @Override
    public String toString() {
        return "\n\t" +
                name;
    }

    @Override
    public int compareTo(Meal o) {
        return this.calorie - o.calorie;
    }
}
