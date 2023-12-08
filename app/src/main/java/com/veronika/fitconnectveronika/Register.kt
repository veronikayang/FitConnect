package com.veronika.fitconnectveronika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.veronika.fitconnectveronika.config.DBHandler
import com.veronika.fitconnectveronika.model.session

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var dbHandler: DBHandler

        dbHandler = DBHandler(this)


        val editEmail = findViewById<EditText>(R.id.txtEmail)
        val editPassword = findViewById<EditText>(R.id.txtPassword)
        val btnRegister = findViewById<Button>(R.id.ctaSignUp)

        btnRegister.setOnClickListener {
            val stringEmail = editEmail.text.toString()
            val stringPassword = editPassword.text.toString()

            if (stringEmail.isEmpty() or stringPassword.isEmpty()) {
                Toast.makeText(this, "All Fields are Required!", Toast.LENGTH_SHORT).show()
            }

//            Log.d("Register Activity", "Entered Email: $stringEmail")
////            Log.d("Register Activity", "Entered Password: $stringPassword")

            dbHandler.addNewUser(stringEmail, stringPassword)
            var user = dbHandler.getUserByEmail(stringEmail)
            session.sessionActive = true
            session.user = user

            if (session.sessionActive) {
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
            }
        }
    }
}