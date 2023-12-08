package com.veronika.fitconnectveronika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.veronika.fitconnectveronika.model.session

class NutritionPlans : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutritionplans)

        if (session.sessionActive == false) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        var btnBack = findViewById<Button>(R.id.ctaBack)

        var btnSignout = findViewById<Button>(R.id.ctaBack)

        btnBack.setOnClickListener {
            if (session.sessionActive == true) {
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}