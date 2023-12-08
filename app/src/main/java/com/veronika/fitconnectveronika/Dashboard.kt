package com.veronika.fitconnectveronika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.veronika.fitconnectveronika.model.session

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        if (session.sessionActive == false) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        // Set dashboard welcome text
        var lblWelcome = findViewById<TextView>(R.id.lblWelcomeTxt)
        lblWelcome.text = "Hello " + (session.user?.email?.substringBefore("@") ?: "") + "!"

        var btnWorkoutTracking = findViewById<Button>(R.id.ctaWTracking)
        btnWorkoutTracking.setOnClickListener {
            val intent = Intent(this, WorkoutTracking::class.java)
            startActivity(intent)
        }

        var btnNutritionPlans = findViewById<Button>(R.id.ctaNPlan)
        btnNutritionPlans.setOnClickListener {
            val intent = Intent(this, NutritionPlans::class.java)
            startActivity(intent)
        }

            var btnForum = findViewById<Button>(R.id.ctaForum)
        btnForum.setOnClickListener{
            val intent = Intent(this, Forum::class.java)
            startActivity(intent)
        }

        var btnClasses = findViewById<Button>(R.id.ctaClasses)
        btnClasses.setOnClickListener{
            val intent = Intent(this, Classes::class.java)
            startActivity(intent)
        }

        var btnSignout = findViewById<Button>(R.id.ctaBack)
        var btnProfile = findViewById<Button>(R.id.ctaProfile)
        btnProfile.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        btnSignout.setOnClickListener {
            session.sessionActive = false
            session.user = null
            if (session.sessionActive == false) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}