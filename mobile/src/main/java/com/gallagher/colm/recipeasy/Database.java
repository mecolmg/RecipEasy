package com.gallagher.colm.recipeasy;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by jacobcontreras on 12/2/16.
 */

public class Database {


    public static final String PREFS_NAME = "recipez";
    SharedPreferences sharedPref;
    SharedPreferences.Editor prefEditor;
    public MainActivity mainActivity;
    //public Context context;
    public ArrayList<String> favoritesList; //name of favorite recipies
    public HashMap<String, ArrayList<String>> ingredientsMap; //list of ingredients that can be retrieved using favorites name
    public HashMap<String, ArrayList<String>> directionsMap; //list of directions that can be retrieved using favorites name

    public Database(MainActivity mainActivity){
        favoritesList = new ArrayList<>();
        ingredientsMap = new HashMap<>();
        directionsMap = new HashMap<>();
        this.mainActivity = mainActivity;

        sharedPref = mainActivity.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefEditor = mainActivity.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
    }

    public void setFavorite(String fav, ArrayList<String> ingredients, ArrayList<String> directions){
        favoritesList.add(fav);
        ingredientsMap.put(fav, ingredients);
        directionsMap.put(fav, directions);

    }

    public ArrayList<String> getDirections(String fav){
        return directionsMap.get(fav);
    }

    public ArrayList<String> getIngredients(String fav){
        return ingredientsMap.get(fav);
    }

    public boolean removeFavorite(String fav){
        Object check;
        Object check2;
        check = directionsMap.remove(fav);
        check2 = ingredientsMap.remove(fav);

        return (check != null && check2 != null);
    }

    public void storeData(){
        //sharedPref.

    }

    public void setDataLocally(){

    }

    public void printDatabase(){ //method for testing the functionality of the database
        System.out.println("printing info. from the database...");
        for(String fav : favoritesList){
            System.out.println(fav);
            for(String ingredient : ingredientsMap.get(fav)){
                System.out.println(ingredient);
            }
            for(String direction : directionsMap.get(fav)){
                System.out.println(direction);
            }
        }
    }

}
