package space.kingshiba1;

import java.util.Arrays;

public class Day {
    private Meal[] meals;
    private int total_protein;
    private int total_cals;
    private int day;
    private int numMeals = 0;

    public Day(int d){
        day = d + 1;
        meals = new Meal[10];
    }

    public void addMeal(Meal m){
        meals[numMeals] = m;
        numMeals++;
    }

    public Meal[] getMeals(){
        return meals;
    }

    public void setUp(){
        Meal[] newMeals = new Meal[numMeals];
        for(int i = 0; i < numMeals; i++){
            newMeals[i] = meals[i];
        }
        meals = newMeals;
    }

    private void calcTotals(){
        for(Meal m: meals){
            total_cals += m.getCal();
            total_protein += m.getProtein();
        }
    }

    @Override
    public String toString() {
        setUp();
        calcTotals();
        return "Day " + day +
                Arrays.toString(meals) +
                ", \ntotal_protein=" + total_protein +
                ", total_cals=" + total_cals +
                '}';
    }
}
