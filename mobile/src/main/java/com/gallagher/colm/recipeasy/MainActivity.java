package com.gallagher.colm.recipeasy;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    //
    private Toolbar toolbar;
    private Database database;
    String tempFav;
    Set<String> tempDirectionSet;
    Set<String> tempIngredientsSet;

    EditText favEditText;
    EditText ingredientEditText;
    EditText directionEditText;

    Button ingredientButton;
    Button directionButton;
    Button submitToDatabaseButton;
    Button getDatabaseInfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Database(this);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tempFav = "";
        tempDirectionSet = new HashSet<>();
        tempIngredientsSet = new HashSet<>();

        favEditText = (EditText) findViewById(R.id.favorite);
        ingredientEditText = (EditText) findViewById(R.id.ingredient);
        directionEditText = (EditText) findViewById(R.id.direction);

        ingredientButton = (Button) findViewById(R.id.ingredientButton);
        directionButton = (Button) findViewById(R.id.directionButton);
        submitToDatabaseButton = (Button) findViewById(R.id.databaseButton);
        getDatabaseInfoButton = (Button) findViewById(R.id.databaseInfoButton);

        ingredientButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addIngredient();
            }

        });

        directionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addDirection();
            }

        });

        submitToDatabaseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendFavoriteToDatabase();
            }

        });

        getDatabaseInfoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                printDatabase();
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public void printDatabase(){
        database.printDatabase();
    }

    public void addIngredient(){
        String ingredient = ingredientEditText.getText().toString();
        tempIngredientsSet.add(ingredient);
        ingredientEditText.setText("Enter Ingredient");
        System.out.println("ingredient added: " + ingredient);

    }
    public void addDirection(){
        String direction = directionEditText.getText().toString();
        tempDirectionSet.add(direction);
        directionEditText.setText("Submit Direction");
        System.out.println("direction added: " + direction);

    }

    public void sendFavoriteToDatabase(){
        tempFav = favEditText.getText().toString();
        database.setFavorite(tempFav, tempIngredientsSet, tempDirectionSet);
        database.storeDataInStorage();
        favEditText.setText("Enter Favorite Name");
        tempIngredientsSet.clear();
        tempDirectionSet.clear();
        System.out.println("favorite added: " + tempFav);
        tempFav = "";
    }
}
