package com.veronika.fitconnectveronika.config
import android.annotation.SuppressLint
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull
import com.veronika.fitconnectveronika.model.User

class DBHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val DB_NAME = "fitconnect"
        private const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        createUsersTBL(db)
        createProfileTBL(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        upgradeUsersTBL(db)
    }
//Profile Data Stuff
fun createProfileTBL(db: SQLiteDatabase?) {
    val query = "CREATE TABLE profile (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "userid INTEGER PRIMARY KEY, " +
            "name TEXT, " +
            "age TEXT, " +
            "weight TEXT, " +
            "height TEXT, " +
            "fitnessgoals TEXT)"

    db?.execSQL(query)
}
    fun addNewProfile(name: String, age: String, weight: String, height:String, fitnessgoals:String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("age", age)
            put("weight", weight)
            put("height", height)
            put("fitnessgoals", fitnessgoals)
        }

        db.insert("profile", null, values)
        db.close()
    }
    // USER DATA DB STUFF

    fun createUsersTBL(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT, " +
                "password TEXT)"

        db?.execSQL(query)
    }

    fun upgradeUsersTBL(db: SQLiteDatabase?) {
        db?.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addNewUser(userEmail: String, userPassword: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("email", userEmail)
            put("password", userPassword)
        }

        db.insert("users", null, values)
        db.close()
    }

    fun getUserByEmail(email: String): User {
        var user: User
        val db = writableDatabase
        val cursor = db.rawQuery("SELECT * FROM users WHERE email = ?", arrayOf(email))

        user = User(0, "", "")
        cursor.use { it ->
            while (it.moveToNext()) {
                val id = it.getLongOrNull(it.getColumnIndex("id"))
                val email = it.getStringOrNull(it.getColumnIndex("email"))
                val password = it.getStringOrNull(it.getColumnIndex("password"))

                user = User(id, email, password)
            }
        }

        db.close()
        return user
    }
}