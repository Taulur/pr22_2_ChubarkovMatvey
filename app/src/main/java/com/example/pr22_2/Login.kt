package com.example.pr22_2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.gson.Gson
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class Login : AppCompatActivity() {
    private lateinit var login: EditText
    private lateinit var password: EditText
    private lateinit var pref: SharedPreferences
    private lateinit var text1: TextView
    private lateinit var text2: TextView
    private val MY_SETTINGS = "my_settings"

    data class User(var pass: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login = findViewById(R.id.login);
        password = findViewById(R.id.pass);

        pref = getSharedPreferences(MY_SETTINGS, MODE_PRIVATE)
    }


    fun toRegistr(view: View) {
        val intent = Intent(this,Registr::class.java)
        startActivity(intent)
    }
    fun login(view: View) { if (login.text.toString().isNotEmpty() && password.text.toString().isNotEmpty())
    {
        var pass = pref.getString("${login.text}", null)
        var user: User
        try {
            user = Gson().fromJson(pass, User::class.java)
        } catch (e:Exception)
        {
            user = User("")
        }

        if (password.text.toString() == user.pass) {


            val intent = Intent(this,Main::class.java)
            startActivity(intent)

        }
        else
        {
            val snackbar =  Snackbar.make(view,"Неверные данные входа", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }
    else
    {
        val snackbar =  Snackbar.make(view,"Введите данные для входа", Snackbar.LENGTH_LONG)
        snackbar.show()

    }}
}