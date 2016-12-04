package com.gallagher.colm.recipeasy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

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

    SharedPreferences sharedPref;
    SharedPreferences.Editor prefEditor;

    public Activity activity;

    public ArrayList<String> favoritesList; //keys for all of the jsonobjects
    public HashMap<String, JSONObject> jSONMap; //contains all json objects with their cooresponding keys from favorites list

    public Database(Activity activity) throws org.json.JSONException{
        favoritesList = new ArrayList<>();
        jSONMap = new HashMap<>();

        this.activity = activity;

        sharedPref = activity.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefEditor = activity.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        retrieveDataFromStorage();
    }

    public void storeJSONObject(JSONObject jObj) throws org.json.JSONException{
        String keyId = jObj.getString("id");
        if(!favoritesList.contains(keyId)){
            favoritesList.add(keyId);
        }
        jSONMap.put(keyId, jObj);
        String value = jObj.toString();
        if(!sharedPref.contains(keyId)) {
            prefEditor.putString(keyId, value);
        }
        if(sharedPref.contains(FAV_LIST_KEY)){
            prefEditor.remove(FAV_LIST_KEY);
            prefEditor.putStringSet(FAV_LIST_KEY, new HashSet<>(favoritesList));
        }
        prefEditor.commit();
    }

    public JSONObject getJSONObject (String keyId) throws org.json.JSONException{
        return jSONMap.get(keyId);
    }

    public void removeJSONObject(String keyId){
        if(jSONMap.containsKey(keyId)){
            jSONMap.remove(keyId);
        }
        if(sharedPref.contains(keyId)){
            prefEditor.remove(keyId);
        }
        if(favoritesList.contains(keyId)){
            favoritesList.remove(keyId);
        }
        prefEditor.commit();
    }

    public ArrayList<String> getFavoritesList(){
        return favoritesList;
    }

    public void retrieveDataFromStorage() throws org.json.JSONException{
        Set<String> favoritesSet = sharedPref.getStringSet(FAV_LIST_KEY, null);
        if(favoritesSet != null) {
            for (String fav : favoritesSet) {
                favoritesList.add(fav);
                jSONMap.put(fav, new JSONObject(sharedPref.getString(fav, null)));
            }

        }
    }

    public void printDatabase(){ //method for testing the functionality of the database
        System.out.println("printing info. from the database...");
        for(String fav : favoritesList){
            System.out.println(fav);
            System.out.println((jSONMap.get(fav)).toString());
        }
    }

}