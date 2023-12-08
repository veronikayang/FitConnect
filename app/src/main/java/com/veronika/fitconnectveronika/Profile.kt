package com.veronika.fitconnectveronika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.veronika.fitconnectveronika.config.DBHandler
import com.veronika.fitconnectveronika.model.session

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        if (session.sessionActive == false) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        var btnBack = findViewById<Button>(R.id.ctaBack)
        var btnCreate = findViewById<Button>(R.id.ctaCreate)

        btnCreate.setOnClickListener {
            if (session.sessionActive == true) {
                var dbHandler: DBHandler

                dbHandler = DBHandler(this)


                val editName = findViewById<EditText>(R.id.txtName)
                val editAge = findViewById<EditText>(R.id.txtAge)
                val editWeight = findViewById<EditText>(R.id.txtWeight)
                val editHeight = findViewById<EditText>(R.id.txtHeight)
                val editFitnessGoals = findViewById<EditText>(R.id.txtGoal)



                    val stringName = editName.text.toString()
                    val stringAge = editAge.text.toString()
                    val stringWeight = editWeight.text.toString()
                    val stringHeight = editHeight.text.toString()
                    val stringFitnessGoals = editFitnessGoals.text.toString()


                    if (stringName.isEmpty() or stringAge.isEmpty() or stringWeight.isEmpty() or stringWeight.isEmpty() or stringHeight.isEmpty() or stringFitnessGoals.isEmpty()) {
                        Toast.makeText(this, "All Fields are Required!", Toast.LENGTH_SHORT).show()
                    }

//            Log.d("Register Activity", "Entered Email: $stringEmail")
////            Log.d("Register Activity", "Entered Password: $stringPassword")

                    dbHandler.addNewProfile(stringName, stringAge, stringWeight, stringHeight, stringFitnessGoals )
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()

            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

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