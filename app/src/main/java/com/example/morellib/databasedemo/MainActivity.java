package com.example.morellib.databasedemo;

//This app walks you through a few different SQLite Database examples. Read along and learn. NOTE: this app has no functionality if loaded into a device.

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.sql.SQLClientInfoException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Have to surround our code with try/catch
        try {
            //Let's create (or open if already created) our database named Users. Only our app can access this database.
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

            //Let's create a table if it doesn't already exist called users. We want this to contain a name, and an age. The age is capped at 3 ints
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");

            //Now we insert 4 users into our database. They have varying names and ages
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Rob', 34)");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Tommy', 4)");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Sherri', 45)");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('BMo', 26)");

            //Now we create a new table if it doesn't already exist. This table includes a primiary integer.
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INTEGER(3), id INTEGER PRIMARY");
            myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('BMo', 26)");
            myDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('AA', 27)");

            //We can delete users based on their primary int
            myDatabase.execSQL("DELETE FROM newUsers WHERE id = 1");

            //Change the age of a user if the name equals ROB
            //myDatabase.execSQL("UPDATE users SET age = 2 WHERE name = 'Rob'");

            //Delete a user(s) named Rob
            //myDatabase.execSQL("DELETE FROM users WHERE name = 'Rob'");

            //various methods of selecting users from our database based on different criteria
            //Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);
            //Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE age < 18", null);
            //Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE name = 'Rob'", null);
            //Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE name = 'Rob' AND age > 18", null);
            //Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE name LIKE 'k%'", null);
            //Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE name LIKE '%o%'", null);
            Cursor c = myDatabase.rawQuery("DELETE FROM users WHERE name = 'Kirsten' LIMIT 1", null);


            //create int based on our name and age column
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");

            //move to the first name/age
            c.moveToFirst();

            //cycle through users until c == null
            while (c != null){

                //log names and ages
                Log.i("UserResults - name", c.getString(nameIndex));
                Log.i("UserResults - age", Integer.toString(c.getInt(ageIndex)));

                //move to the next user
                c.moveToNext();

            }

        //the end of our try/catch error check
        } catch (Exception e){
        e.printStackTrace();
    }







        //NOTHING BELOW THIS LINE IS UTILIZED IN THIS DEMO

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
