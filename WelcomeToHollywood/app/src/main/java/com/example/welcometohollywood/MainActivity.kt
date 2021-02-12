package com.example.welcometohollywood

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.game_menu, menu)
        return true
    }

    //permet d'accéder à la page de préparation de la partie
    fun rootToPrepareGame(view: View) {
        val prepareGameActivity = Intent(this@MainActivity, PrepareGameActivity::class.java)
        startActivity(prepareGameActivity)
    }

    //permet d'accéder à la page du Règlement
    fun rootToRules(view: View) {
        val rulesActivity = Intent(this@MainActivity, RulesActivity::class.java)
        startActivity(rulesActivity)
    }

    //permet d'accéder à la page du classement
    fun rootToRanking(view: View) {
        val rankingActivity = Intent(this@MainActivity, RankingActivity::class.java)
        startActivity(rankingActivity)
    }
}