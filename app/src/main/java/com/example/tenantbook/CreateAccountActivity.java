package com.example.tenantbook;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    EditText createFullName , createEmailAddress , createPassword , createUserName;
    Button buttonCreateAccount2;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Add your account creation UI and logic here
        createFullName = findViewById(R.id.createFullName);
        createEmailAddress = findViewById(R.id.createEmailAddress);
        createPassword = findViewById(R.id.createPassword);
        createUserName = findViewById(R.id.createUserName);
        buttonCreateAccount2 = findViewById(R.id.buttonCreateAccount2);

        dbHelper = new DbHelper(this);

        buttonCreateAccount2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = createFullName.getText().toString();
                String emailAddress = createEmailAddress.getText().toString();
                String userName = createUserName.getText().toString();
                String password = createPassword.getText().toString();

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();


                values.put(DbHelper.USERDETAILS_USERNAME, userName);
                values.put(DbHelper.USERDETAILS_PASSWORD, password);
                values.put(DbHelper.USERDETAILS_EMAILADDRESS, emailAddress);
                values.put(DbHelper.USERDETAILS_FULLNAME, fullName);


                long newRowId = db.insert(DbHelper.TABLE_USER_DETAILS, null, values);

                db.close();

                Toast.makeText(CreateAccountActivity.this, "Created new Account", Toast.LENGTH_SHORT).show();

            }
        });



    }
}
