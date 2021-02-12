package com.example.welcometohollywood

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.welcometohollywood.Models.Movie

class GameActivity : AppCompatActivity() {
    val tableIdMoviesFound = mutableListOf<Int>()
    var positionArea = -1
    val tableIdZone = Array(5) { mutableListOf<Int>() }
    val positiveButtonClick = { dialog: DialogInterface, which: Int -> this.rootToEndGame() }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        if (intent.hasExtra("teamName")) {
            this.title = intent.getStringExtra("teamName")
        } else {
            this.title = "Équipe"
        }
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movieNameEditText = findViewById<EditText>(R.id.MovieNameEditText)
        movieNameEditText.visibility = View.INVISIBLE
        val validationButton = findViewById<Button>(R.id.validationButton)
        validationButton.visibility = View.INVISIBLE
        this.prepareData()
        this.informStateMovies("-1")
        this.counter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.game_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item?.itemId)
        {
            R.id.clueItem ->
            {
                var nbrClues = item?.title.toString().toInt()
                if(nbrClues <= 0) {
                    Toast.makeText(this,"Aucun indice disponible",Toast.LENGTH_LONG).show()
                }
                else {
                    nbrClues-=1
                    item?.title = nbrClues.toString()
                    Toast.makeText(this,"Indices restants : "+item?.title,Toast.LENGTH_LONG).show()
                }
            }
            R.id.moviesListItem -> {
                Toast.makeText(this,"liste des films trouvés",Toast.LENGTH_LONG).show()
            }
            else -> {
                rootToMain()
            }
        }
        return true
    }

    //stocke les id des films à trouver par zone
    fun prepareData() {
        for(i in 0 until Movies.moviesList.count()) {
            val tmpPosition = this.tableIdZone[Movies.moviesList[i].area].count()
            this.tableIdZone[Movies.moviesList[i].area].add(tmpPosition, Movies.moviesList[i].id)
        }
    }


    //mise à jour de l'affichage du nombre de films trouvés (par zone et général)
    fun informStateMovies(area: String) {
        val nbrMoviesTextView = findViewById<TextView>(R.id.nbrMoviesTextView)
        if(area == "-1") {
            nbrMoviesTextView.text = tableIdMoviesFound.count().toString()+"/"+Movies.moviesList.count().toString()
        } else {
            var tmp = 0
            for(id: Int in this.tableIdMoviesFound) {
                if(this.tableIdZone[area.toInt()].contains(id)) {
                    tmp+=1
                }
            }
            nbrMoviesTextView.text = tmp.toString()+"/"+this.tableIdZone[area.toInt()].count().toString()
        }
    }


    fun updatePositionArea(area: String) {
        val movieNameEditText = findViewById<EditText>(R.id.MovieNameEditText)
        val validationButton = findViewById<Button>(R.id.validationButton)
        if(area.equals(this.positionArea.toString())) {
            this.informStateMovies("-1")
            this.positionArea = -1
            movieNameEditText.visibility = View.GONE
            validationButton.visibility = View.GONE
        }
        else {
            this.informStateMovies(area)
            this.positionArea = area.toInt()
            val nbrMoviesTextView = findViewById<TextView>(R.id.nbrMoviesTextView)
            val tmp = nbrMoviesTextView.text.toString().split('/')
            if(tmp[0] == tmp[1]) {
                movieNameEditText.visibility = View.GONE
                validationButton.visibility = View.GONE
            }

            else {
                movieNameEditText.visibility = View.VISIBLE
                validationButton.visibility = View.VISIBLE
            }
        }
    }

    fun areaListener(view: View) {
        this.updatePositionArea(view.tag.toString())
    }

    fun controlNameMovie(view: View) {
        if(this.positionArea == -1) {
            return
        }
        val nameMovieEditText = findViewById<EditText>(R.id.MovieNameEditText)
        for(id in this.tableIdZone[this.positionArea]) {
            if(!this.tableIdMoviesFound.contains(id)) {
                if(Movies.moviesList[id].compareName(nameMovieEditText.text.toString())) {
                    this.tableIdMoviesFound.add(this.tableIdMoviesFound.count(), id)
                    nameMovieEditText.setText("")
                    updatePositionArea(this.positionArea.toString())
                    personalisedAlert("Bonne réponse")
                    controlEndGame()
                    return
                }
            }
        }
        personalisedAlert("Mauvaise réponse")
        nameMovieEditText.setText("")
    }

    fun personalisedAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setMessage(message)
            setPositiveButton("OK", null)
            show()
        }
    }

    fun endPersonalisedAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setMessage(message)
            setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
            show()
        }
    }

    private fun controlEndGame() {
        if(this.tableIdMoviesFound.count() == Movies.moviesList.count()) {
            endPersonalisedAlert("Fin de partie")
        }
    }
    //timer de jeu
    @RequiresApi(Build.VERSION_CODES.N)
    fun counter() {
        val timer = findViewById<Chronometer>(R.id.timer)
        timer.isCountDown = true
        timer.base = SystemClock.elapsedRealtime() + 20000
        timer.setOnChronometerTickListener {
            if ("00:00" == timer.text.toString()) {
                timer.stop()
                endPersonalisedAlert("fin du temps")
            }
        }
        timer.start()
    }

    fun rootToMain() {
        val timer = findViewById<Chronometer>(R.id.timer)
        timer.stop()
        val mainActivityIntent = Intent(this@GameActivity, MainActivity::class.java)
        startActivity(mainActivityIntent)
    }

    fun rootToEndGame() {
        val endGameActivityIntent = Intent(this@GameActivity, EndGameActivity::class.java)
        endGameActivityIntent.putExtra("teamName", this.title)
        val score = this.tableIdMoviesFound.count()
        endGameActivityIntent.putExtra("score", score)
        startActivity(endGameActivityIntent)
    }
}