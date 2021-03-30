package space.kingshiba1;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        //all of the meals, add more if you'd like
        Day[] plan = new Day[6];
        Meal[] breakfast = new Meal[]{
                new Meal("oatmeal", 668, 35, new Ingredient[]{new Ingredient("oatmeal", 1, "cup"), new Ingredient("protein pow", 1, "scoop"), new Ingredient("brown sugar", 2, "tbsp"), new Ingredient("peanut butter", 2, "tbsp")}, false),
                new Meal("scrambled eggs", 594, 38, new Ingredient[]{new Ingredient("eggs", 6, "large"), new Ingredient("butter", 1, "tbsp"), new Ingredient("salsa", .25, "jar")}, false)
        };
        Meal[] lunch = new Meal[]{
                new Meal("sandwich", 832, 60, new Ingredient[]{new Ingredient("ham", 0, ""), new Ingredient("salami", 0, ""), new Ingredient("provalone", 0, ""), new Ingredient("bread", 0, "")}, false),
                new Meal("fried rice", 620, 18, new Ingredient[]{new Ingredient("butter", 4, "tbsp"), new Ingredient("eggs", 2, "large"), new Ingredient("carrots", 2, "medium"), new Ingredient("onion", 1, "small"), new Ingredient("green onion", 3, "full")}, true)
        };
        Meal[] dinner = new Meal[]{
                new Meal("steak", 440, 46, new Ingredient[]{new Ingredient("new york strip", 8, "oz")}, false),
                new Meal("air fried chicken", 831, 53, new Ingredient[]{new Ingredient("chicken", 8, "oz"), new Ingredient("flour", 1, "cup"), new Ingredient("bread crumbs", 1, "cup")}, false),
                new Meal("midnight pasta", 890, 32, new Ingredient[]{new Ingredient("spaghetti", 8, "oz"), new Ingredient("anchovies", 4, "full"), new Ingredient("capers", 1, "tsp"), new Ingredient("garlic", 3, "cloves")}, false),
                new Meal("seared salmon", 1037, 82, new Ingredient[]{new Ingredient("Salmon", 14, "oz"), new Ingredient("lemon juice", 0, null), new Ingredient("olive oil", 0, null), new Ingredient("butter", 2, "tbsp"), new Ingredient("garlic", 2, "cloves"), new Ingredient("parsley", 1, "cup")}, false),
                new Meal("takeout", 1000, 40, new Ingredient[]{new Ingredient("whatever", 0, null)}, false)
        };
        Meal[] sides = new Meal[]{
                new Meal("rice", 242, 5, new Ingredient[]{new Ingredient("rice", 2, "cup"), new Ingredient("butter", 1, "tbsp")}, false),
                new Meal("sautaaed spinach", 244, 5, new Ingredient[]{new Ingredient("spinach", 6, "cup"), new Ingredient("butter", 2, "tbsp")}, false),
                new Meal("potatos", 263, 9, new Ingredient[]{new Ingredient("potato", 4, "medium"), new Ingredient("grated parm cheese", .25, "cup"), new Ingredient("garlic salt", 0, null)}, true)
        };

        int calRemaigning = 2300; //set this varible to however many calories you want per day, it is a minimum so you will always get at least that many
        Random rng = new Random();
        Meal[] dinnerClone = dinner.clone();
        for(int i = 0; i < 6; i++){
            calRemaigning = 2300;
            Day d = new Day(i);
            plan[i] = d;
            int brek = rng.nextInt(2);
            int lun = rng.nextInt(2);
            int din = rng.nextInt(5);
            int sid = rng.nextInt(3);
            d.addMeal(breakfast[brek]);
            d.addMeal(lunch[lun]);
            while(true) {
                if (!dinner[din].recentlyEaten) {
                    d.addMeal(dinner[din]);
                    dinner[din].recentlyEaten = true;
                    break;
                } else {
                    dinner[din].recentlyEaten = false;
                    din = rng.nextInt(5);
                }
            }
            d.addMeal(sides[sid]);
            calRemaigning -= breakfast[brek].getCal();
            calRemaigning -= lunch[lun].getCal();
            calRemaigning -= dinner[din].getCal();
            calRemaigning -= sides[sid].getCal();
            while(true) {
                if(calRemaigning > 0) {
                    sid = rng.nextInt(3);
                    d.addMeal(sides[sid]);
                    calRemaigning -= sides[sid].getCal();
                } else {
                    break;
                }
            }
        }
        for(Day d: plan){
            d.setUp();
        }
        HashMap<String, String> units = generateUnits(plan);
        HashMap<String, Double> list = generateList(plan);
        Set<String> keys = list.keySet();
        for(String n: keys){
            System.out.printf("%-20s %-20s %-20s\n", n , String.valueOf(list.get(n)), units.get(n));
        }
        for(Day d: plan){
            System.out.println(d);
        }
    }

    public static HashMap<String, Double> generateList(Day[] plan){
        HashMap<String, Double> list = new HashMap<String, Double>();
        for(Day d: plan){
            for(Meal m: d.getMeals()){
                for(Ingredient i: m.getIngredients()){
                    if(list.containsKey(i.getName())){
                        double based = list.get(i.getName());
                        list.replace(i.getName(), i.getAmount()+based);
                    } else {
                        list.put(i.getName(), i.getAmount());
                    }
                }
            }
        }

        return list;
    }

    public static HashMap<String, String> generateUnits(Day[] plan){
        HashMap<String, String> list = new HashMap<String, String>();
        for(Day d: plan){
            for(Meal m: d.getMeals()){
                for(Ingredient i: m.getIngredients()){
                    if(list.containsKey(i.getName())){

                    } else {
                        list.put(i.getName(), i.getUnit());
                    }
                }
            }
        }

        return list;
    }
}
