package com.example.pigolevmyapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.AppCompatImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class DetailsActivity : AppCompatActivity() {

    private val film: Film
        get() = intent.extras?.get("film") as Film

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        toolbarInit()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val appBar = findViewById<AppBarLayout>(R.id.app_bar)
        var detailsDescription = findViewById<TextView>(R.id.details_description)

        menuInit(bottomNavigation, detailsDescription)

    }

    private fun menuInit(
        bottomNavigation: BottomNavigationView,
        detailsDescription: TextView
    ) {
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.favorites -> {
                    val snackbarF =
                        Snackbar.make(detailsDescription, "Избранное", Snackbar.LENGTH_SHORT)
                    snackbarF.anchorView = bottomNavigation
                    snackbarF.show()
                    true
                }
                R.id.watch_later -> {
                    val snackbarWL =
                        Snackbar.make(detailsDescription, "Отложенное", Snackbar.LENGTH_SHORT)
                    snackbarWL.anchorView = bottomNavigation
                    snackbarWL.show()
                    true
                }
                else -> false
            }
        }
    }

    private fun toolbarInit() {
        val details_toolbar = findViewById<Toolbar>(R.id.details_toolbar)
        val details_poster = findViewById<AppCompatImageView>(R.id.details_poster)
        val details_description = findViewById<TextView>(R.id.details_description)
        details_toolbar.title = film.title
        details_poster.setImageResource(film.poster)
        details_description.text = film.description
    }
}