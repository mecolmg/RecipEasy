package com.gallagher.colm.recipeasy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jacobcontreras on 12/1/16.
 */

public class RecipeViewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ArrayList<String> sampleDataIngredients = new ArrayList<>(Arrays.asList("chicken (1 Cup)", "Carrots (1/2 Cup)", "Gravy (1/2 Cup)"));
    private ArrayList<String> sampleDataDirections = new ArrayList<>(Arrays.asList("find the chicken", "make the chicken", "do something with the carrots"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

}
