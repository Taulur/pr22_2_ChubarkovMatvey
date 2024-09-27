package com.example.pr22_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.bumptech.glide.Glide
import org.json.JSONObject

class Main : AppCompatActivity() {
    lateinit var search: EditText
    lateinit var title: TextView
    lateinit var year: TextView
    lateinit var runtime: TextView
    lateinit var genre: TextView
    lateinit var poster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search = findViewById<EditText>(R.id.search)
        title = findViewById<TextView>(R.id.title)
        year = findViewById<TextView>(R.id.year)
        runtime = findViewById<TextView>(R.id.runtime)
        genre = findViewById<TextView>(R.id.genres)
        poster = findViewById<ImageView>(R.id.poster)



    }

    fun findFilm(view: View) {if (search.text.toString().isNotEmpty() && search.text.toString() != null) {
        var key = "136c658a"
        var url = "https://www.omdbapi.com/?i=tt3896198&apikey=" + key + "&t=" + search.text.toString()
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                    response ->
                val obj = JSONObject(response)

                val filmName = obj.getString("Title")
                title.text = filmName

                val filmYear = obj.getInt("Year")
                year.text = filmYear.toString()

                 val filmRuntime = obj.getString("Runtime")
                runtime.text = filmRuntime

                 val filmGenre = obj.getString("Genre")
                genre.text = filmGenre

                val imageUrl = obj.getString("Poster")

                if (imageUrl != null) {
                    Glide.with(this)
                        .load(imageUrl)
                        .placeholder(R.drawable.icon)
                        .error(R.drawable.icon)
                        .into(poster)
                }


            },
            {

                val snackbar =  Snackbar.make(view,"Фильм не найден!", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        )
        queue.add(stringRequest)
    }
    else
    {

        val snackbar =  Snackbar.make(view,"Введите название фильма", Snackbar.LENGTH_LONG)
        snackbar.show()
    }}


}