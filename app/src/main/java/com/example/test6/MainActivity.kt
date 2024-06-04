package com.example.test6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewSalon = findViewById<TextView>(R.id.textViewSalon)
        val textViewWelcome = findViewById<TextView>(R.id.textViewWelcome)

        // Завантаження анімації
        val slideUpAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up)

        // Застосування анімації до TextView
        textViewSalon.startAnimation(slideUpAnimation)
        textViewWelcome.startAnimation(slideUpAnimation)

        // Перевірка, чи є savedInstanceState порожнім перед створенням нового інтенту
        if (savedInstanceState == null) {
            // Затримка на 3 секунди перед переходом до SecondActivity
            Handler().postDelayed({
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000) // Затримка триває 3000 мілісекунд (тобто 3 секунди)
        }
    }
}