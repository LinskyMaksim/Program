package com.example.test6;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
public class ThirdActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Затримка на 1 секунду перед переходом до ViewBookingsActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ThirdActivity.this, ViewBookingsActivity.class);
                startActivity(intent);
                // Не закриваємо поточну активність
            }
        }, 1000); // Затримка в мілісекундах
    }
}
