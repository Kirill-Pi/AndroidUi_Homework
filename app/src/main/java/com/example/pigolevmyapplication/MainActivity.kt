package com.example.pigolevmyapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.NavigationMenu

class MainActivity : AppCompatActivity() {

    val filmsDataBase = mutableListOf<Film>(
        Film("Dr. No", R.drawable.drno, "A resourceful British government agent seeks answers in a case involving the disappearance of a colleague and the disruption of the American space program."),
        Film("From Russia with Love", R.drawable.frwl, "James Bond willingly falls into an assassination plot involving a naive Russian beauty in order to retrieve a Soviet encryption device that was stolen by S.P.E.C.T.R.E."),
        Film("Goldfinger", R.drawable.gf, "While investigating a gold magnate's smuggling, James Bond uncovers a plot to contaminate the Fort Knox gold reserve."),
        Film("Thunderball", R.drawable.tb, "James Bond heads to the Bahamas to recover two nuclear warheads stolen by S.P.E.C.T.R.E. Agent Emilio Largo in an international extortion scheme."),
        Film("You Only Live Twice", R.drawable.yolt, "James Bond and the Japanese Secret Service must find and stop the true culprit of a series of space hijackings, before war is provoked between Russia and the United States."),
        Film("Live and Let Die", R.drawable.lald, "James Bond is sent to stop a diabolically brilliant heroin magnate armed with a complex organisation and a reliable psychic tarot card reader."),
        Film("Moonraker", R.drawable.mr, "James Bond investigates the mid-air theft of a space shuttle, and discovers a plot to commit global genocide."),
        Film(" Octopussy", R.drawable.op, "A fake Fabergé egg, and a fellow Agent's death, lead James Bond to uncover an international jewel-smuggling operation, headed by the mysterious Octopussy, being used to disguise a nuclear attack on N.A.T.O. forces."),
        Film("The Living Daylights", R.drawable.tld, "James Bond is sent to investigate a KGB policy to kill all enemy spies and uncovers an arms deal that potentially has major global ramifications."),
        Film("Tomorrow Never Dies", R.drawable.tnd, "James Bond sets out to stop a media mogul's plan to induce war between China and the UK in order to obtain exclusive global media coverage."),

    )

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menuInit()

        var mainRecycler = findViewById<RecyclerView>(R.id.main_recycler)
        mainRecycler.apply {

           filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener{

               override fun click(film: Film) {
                   val bundle = Bundle()
                   bundle.putParcelable("film", film)
                   val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                   startActivity(intent)
                   intent.putExtras(bundle)
                   startActivity(intent)
               }
           })
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }

        filmsAdapter.addItems(filmsDataBase)

    }

    private fun menuInit() {
        var topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)

        topAppBar.setNavigationOnClickListener {
            Toast.makeText(this, "Кинопоиск...", Toast.LENGTH_SHORT).show()
        }
        topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        var bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)


        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.favorites -> {
                    Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.watch_later -> {
                    Toast.makeText(this, "Отложенное", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.library -> {
                    Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}

