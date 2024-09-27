package com.example.pr22_2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.gson.Gson
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class Registr : AppCompatActivity() {
    private lateinit var login: EditText
    private lateinit var pass1: EditText
    private lateinit var pass2: EditText
    private lateinit var pref: SharedPreferences
    private val MY_SETTINGS = "my_settings"

    data class User(var pass: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registr)
        pref = getSharedPreferences(MY_SETTINGS, MODE_PRIVATE)

        login = findViewById(R.id.loginRegist)
        pass1 = findViewById(R.id.pass1Registr)
        pass2 = findViewById(R.id.pass2Registr)
    }

    fun toLogin(view: View) {
        val intent = Intent(this,Login::class.java)
        startActivity(intent)
    }
    fun register(view: View) {
        if (login.text.toString().isNotEmpty() && pass1.text.toString().isNotEmpty() && pass2.text.toString().isNotEmpty())
    {
            if (pass1.text.toString() == pass2.text.toString()) {

                val ed = pref.edit()

                var passUser = User(pass1.text.toString())
                var user = Gson().toJson(passUser)
                ed.putString(login.text.toString(), user)

                ed.commit()


                val intent = Intent(this,Login::class.java)
                startActivity(intent)



            }
        else
            {
                val snackbar =  Snackbar.make(view,"Пароли не совпадают", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
    }
        else
        {
            val snackbar =  Snackbar.make(view,"Заполните данные", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }
}