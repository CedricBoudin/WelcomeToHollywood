package com.example.welcometohollywood

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONException

class RankingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        this.title = "Classement"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.prepareRank()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    fun prepareRank() {
        val preferences = getSharedPreferences("gameData", Context.MODE_PRIVATE)
        var text = ""
        for (i in 0..9) {
            val name = preferences.getString("rank$i", "")
            text = text+(i+1).toString()+" - $name\n"
        }
        val rankingTextView = findViewById<TextView>(R.id.rankingTextView)
        rankingTextView.text = text
    }


}