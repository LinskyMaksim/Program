package com.example.test6;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class ViewBookingsActivity extends Activity {
    private DatabaseHelper dbHelper;
    private ListView listView;
    private Button buttonExit;
    private Button buttonClearDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);

        dbHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);
        buttonExit = findViewById(R.id.buttonExit);
        buttonClearDatabase = findViewById(R.id.buttonClearDatabase); // Ініціалізовано кнопку очищення бази даних

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Закриваємо активність
                finishAffinity();
            }
        });

        buttonClearDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDatabase(); // Викликаємо метод для очищення бази даних
                displayBookings(); // Оновлюємо відображення після очищення
            }
        });

        displayBookings();
    }

    private void displayBookings() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<String> bookingList = new ArrayList<>();

        // Отримуємо всі записи з бази даних
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_USER, null);

        // Якщо є дані, додаємо їх до списку
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                @SuppressLint("Range") String surname = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SURNAME));
                @SuppressLint("Range") String phoneNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE_NUMBER));
                bookingList.add(name + " " + surname + ", " + phoneNumber);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // Відображаємо дані у ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookingList);
        listView.setAdapter(adapter);
    }

    private void clearDatabase() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_USER, null, null); // Видаляємо всі записи з таблиці
        db.close();
    }
}
