package com.bakanito.bakanitoapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.bakanito.bakanitoapp.R

private lateinit var bPokedex: Button
private lateinit var bMovies: Button
private lateinit var bDogs: Button

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        initComponents()
        initListeners()
    }

    private fun initComponents() {
        bPokedex = findViewById(R.id.bPokedex)
        bMovies = findViewById(R.id.bMovies)
        bDogs = findViewById(R.id.bDogs)
    }

    private fun initListeners() {
        bPokedex.setOnClickListener {
            val intent  = Intent(this, PokedexActivity::class.java)
            startActivity(intent)
        }
        bMovies.setOnClickListener {
            val intent = Intent(this, MoviesActivity::class.java)
            startActivity(intent)
        }

        bDogs.setOnClickListener {
            val intent = Intent(this, DogsActivity::class.java)
            startActivity(intent)
        }
    }
}