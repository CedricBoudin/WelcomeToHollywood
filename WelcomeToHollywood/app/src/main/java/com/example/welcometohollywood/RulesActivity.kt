package com.example.welcometohollywood

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class RulesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)
        this.title = "Règlement"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val rulesTextView = findViewById<TextView>(R.id.rulesTextView)

        var t = "start\n"
        for (i in 0..100) {
            t+=(i.toString()+"\n")
        }
        rulesTextView.text = t
        rulesTextView.movementMethod = ScrollingMovementMethod()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}