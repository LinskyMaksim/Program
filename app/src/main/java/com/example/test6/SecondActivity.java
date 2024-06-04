package com.example.test6;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity {
    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextPhoneNumber;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        dbHelper = new DatabaseHelper(this);

        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);

        Button buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String surname = editTextSurname.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();

                if (isValidInput(name, surname, phoneNumber)) {
                    // Отримуємо запис бази даних
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(DatabaseHelper.COLUMN_NAME, name);
                    values.put(DatabaseHelper.COLUMN_SURNAME, surname);
                    values.put(DatabaseHelper.COLUMN_PHONE_NUMBER, phoneNumber);
                    long newRowId = db.insert(DatabaseHelper.TABLE_USER, null, values);
                    db.close();

                    // Показуємо повідомлення про успішне бронювання
                    Toast.makeText(SecondActivity.this, "Дані збережено в базі даних", Toast.LENGTH_SHORT).show();

                    // Створюємо інтент для переходу до третьої активності
                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    startActivity(intent);
                } else {
                    // Показуємо повідомлення про неправильні введені дані
                    Toast.makeText(SecondActivity.this, "Введіть коректно свої дані", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Додано функціонал для кнопки "Переглянути список"
        Button buttonViewList = findViewById(R.id.buttonViewList);
        buttonViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Створюємо інтент для переходу до активності для перегляду списку
                Intent intent = new Intent(SecondActivity.this, ViewBookingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidInput(String name, String surname, String phoneNumber) {
        return name.length() >= 2 && surname.length() >= 2 && phoneNumber.length() >= 10 && phoneNumber.startsWith("0") || phoneNumber.startsWith("+380");
    }
}


