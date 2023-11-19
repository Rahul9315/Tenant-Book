package com.example.tenantbook;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

public class property_activity_2 extends AppCompatActivity {

    private LinearLayout linearLayoutContent;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linearLayoutContent = findViewById(R.id.linearLayoutContent);
        dbHelper = new DbHelper(this);

        // Load existing buttons from the database
        loadButtons();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_in_menu) {
            Toast.makeText(this, "You Clicked Add Button", Toast.LENGTH_SHORT).show();
            showPopupWindow();

        }

        if (id == R.id.delete_in_menu) {
            Toast.makeText(this, "You Clicked Delete Button", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    private void showPopupWindow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Property Name");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            // Create a new Button with the entered text
            String buttonText = input.getText().toString();
            addButton(buttonText);
            saveButtonToDatabase(buttonText);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void addButton(String buttonText) {
        // Create a new Button with the entered text
        Button button = new Button(this);
        button.setText(buttonText);

        // Add an onClickListener to the button if needed
        button.setOnClickListener(view -> {
            // Handle button click event
            Toast.makeText(this, "Button Clicked: " + buttonText, Toast.LENGTH_SHORT).show();
        });

        // Add the new Button to the LinearLayout
        linearLayoutContent.addView(button);
    }

    private void loadButtons() {
        Cursor cursor = dbHelper.loadButtons();
        while (cursor.moveToNext()) {
            String buttonName = cursor.getString(cursor.getColumnIndex(DbHelper.PROPERTY_NAME));
            addButton(buttonName);
        }
        cursor.close();
    }

    private void saveButtonToDatabase(String buttonName) {
        long result = dbHelper.saveButtonToDatabase(buttonName);
        if (result != -1) {
            // Button saved successfully
            Toast.makeText(this, "Button saved to database", Toast.LENGTH_SHORT).show();
        } else {
            // Error saving button
            Toast.makeText(this, "Error saving button", Toast.LENGTH_SHORT).show();
        }
    }
}