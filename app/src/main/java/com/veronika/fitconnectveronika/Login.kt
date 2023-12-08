package com.veronika.fitconnectveronika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.veronika.fitconnectveronika.config.DBHandler
import com.veronika.fitconnectveronika.model.session

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var dbHandler: DBHandler = DBHandler(this)


        val editEmail = findViewById<EditText>(R.id.txtEmail)
        val editPassword = findViewById<EditText>(R.id.txtPassword)
        val btnLogin = findViewById<Button>(R.id.ctaBack)

        btnLogin.setOnClickListener {
            val stringEmail = editEmail.text.toString()
            val stringPassword = editPassword.text.toString()

            var valid: Boolean= true

            if (stringEmail.isEmpty() or stringPassword.isEmpty()) {
                Toast.makeText(this, "All Fields are Required!", Toast.LENGTH_SHORT).show()
                valid = false
            }

            var user = dbHandler.getUserByEmail(stringEmail)

            if (stringEmail == user.email && stringPassword == user.password && valid) {
                session.sessionActive = true
                session.user = user

                if (session.sessionActive) {
                    val intent = Intent(this, Dashboard::class.java)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "User Does Not Exist!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}