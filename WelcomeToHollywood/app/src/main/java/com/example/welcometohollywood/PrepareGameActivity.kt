package com.example.welcometohollywood

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PrepareGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prepare_game)
        this.title = "Nouvelle partie"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    fun rootToGame(view: View) {
        val editText = findViewById<EditText>(R.id.nameEditText)
        val name = editText.text.toString().trim()
        if (name.isNullOrEmpty()) {
            val toast = Toast.makeText(applicationContext, "Veuillez entrer un nom d'équipe", Toast.LENGTH_LONG)
            toast.show()
            return
        }
        val gameActivityIntent = Intent(this@PrepareGameActivity, GameActivity::class.java)
        gameActivityIntent.putExtra("teamName", name)
        startActivity(gameActivityIntent)
    }
}