package com.gallagher.colm.recipeasy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by jacobcontreras on 12/2/16.
 */

public class Database {


    public static final String PREFS_NAME = "recipez";
    public static final String FAV_LIST_KEY = "favorites";
    public static final String INGREDIENTS_SUFIX_KEY = "ingredients";
    public static final String DIRECTIONS_SUFIX_KEY = "directions";
    //public static final String  = "recipez";

    SharedPreferences sharedPref;
    SharedPreferences.Editor prefEditor;
    public Activity activity;
    //public Context context;
    public ArrayList<String> favoritesList; //name of favorite recipies
    public HashMap<String, Set<String>> ingredientsMap; //list of ingredients that can be retrieved using favorites name
    public HashMap<String, Set<String>> directionsMap; //list of directions that can be retrieved using favorites name

    public Database(Activity activity){
        favoritesList = new ArrayList<>();
        ingredientsMap = new HashMap<>();
        directionsMap = new HashMap<>();
        this.activity = activity;

        sharedPref = activity.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefEditor = activity.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        retrieveDataFromStorage();
    }

    public void setFavorite(String fav, Set<String> ingredients, Set<String> directions){
        favoritesList.add(fav);
        ingredientsMap.put(fav, ingredients);
        directionsMap.put(fav, directions);

    }

    public Set<String> getDirections(String fav){
        return directionsMap.get(fav);
    }

    public Set<String> getIngredients(String fav){
        return ingredientsMap.get(fav);
    }

    public void removeFavorite(String fav){
        Object check;
        Object check2;
        check = directionsMap.remove(fav);
        check2 = ingredientsMap.remove(fav);
        if(check != null && check2 != null){
            prefEditor.remove(fav);
            prefEditor.remove(fav + INGREDIENTS_SUFIX_KEY);
            prefEditor.remove(fav + DIRECTIONS_SUFIX_KEY);
            prefEditor.commit();
        }

    }

    public void storeDataInStorage(){
        Set<String> favoritesSet = new HashSet<>(favoritesList);
        prefEditor.putStringSet(FAV_LIST_KEY, favoritesSet);
        for(String favorite : favoritesList){
            Set<String> ingredientSet = new HashSet<>(ingredientsMap.get(favorite));
            prefEditor.putStringSet(favorite+INGREDIENTS_SUFIX_KEY, ingredientSet);
            Set<String> directionSet = new HashSet<>(directionsMap.get(favorite));
            prefEditor.putStringSet(favorite+DIRECTIONS_SUFIX_KEY, directionSet);
        }

        prefEditor.commit();
    }

    public void retrieveDataFromStorage(){
        Set<String> favoritesSet = sharedPref.getStringSet(FAV_LIST_KEY, null);
        if(favoritesSet != null) {


            for (String fav : favoritesSet) {
                favoritesList.add(fav);
                ingredientsMap.put(fav, sharedPref.getStringSet((fav + INGREDIENTS_SUFIX_KEY), null));
                directionsMap.put(fav, sharedPref.getStringSet((fav + DIRECTIONS_SUFIX_KEY), null));
            }

        }

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