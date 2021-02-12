package com.example.welcometohollywood

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class EndGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_game)
        this.prepareView()
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onSupportNavigateUp(): Boolean {
        rootToMain()
        return true
    }

    fun prepareView() {
        val scoreTextView = findViewById<TextView>(R.id.scoreTextView)
        if (intent.hasExtra("teamName")) {
            this.title = intent.getStringExtra("teamName")
        } else {
            this.title = "Équipe"
        }
        val nbrMovies = Movies.moviesList.count()
        val score = "Score final : "+intent.getIntExtra("score", -1).toString()+"/"+nbrMovies.toString()
        scoreTextView.text = score
    }

    fun updateRank(name: String, score: Int) {
        //EN COURS
        val preferences = getSharedPreferences("gameData", Context.MODE_PRIVATE)

        var text = ""
        for (i in 0..9) {
            val name = preferences.getString("rank$i", "")

        }
        val rankingTextView = findViewById<TextView>(R.id.rankingTextView)
        rankingTextView.text = text
    }

    fun rootToMain() {
        val mainActivityIntent = Intent(this@EndGameActivity, MainActivity::class.java)
        startActivity(mainActivityIntent)
    }
}