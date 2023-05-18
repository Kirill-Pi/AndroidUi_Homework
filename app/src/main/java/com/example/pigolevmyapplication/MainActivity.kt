package com.example.pigolevmyapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pigolevmyapplication.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val onBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if (supportFragmentManager.backStackEntryCount == 1) {
                    showAppClosingDialog()
                }
                else supportFragmentManager.popBackStack()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack("home")
            .commit()
        menuInit()
    }

    fun launchDetailsFragment(film: Film) {

        val bundle = Bundle()
        bundle.putParcelable("film", film)
        val fragment = DetailsFragment()
        fragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack("film")
            .commit()
    }

    private fun menuInit() {

        var topAppBar = binding.topAppBar

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

        var bottomNavigation = binding.bottomNavigation

        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.favorites -> {
                    val snackbarFavorites =
                        Snackbar.make(bottomNavigation, "Избранное", Snackbar.LENGTH_SHORT)
                    snackbarFavorites.anchorView = bottomNavigation
                    snackbarFavorites.show()
                    true
                }
                R.id.watch_later -> {
                    val snackbarWL =
                        Snackbar.make(bottomNavigation, "Отложенное", Snackbar.LENGTH_SHORT)
                    snackbarWL.anchorView = bottomNavigation
                    snackbarWL.show()
                    true
                }
                R.id.library -> {
                    val snackbarLibrary =
                        Snackbar.make(bottomNavigation, "Подборка", Snackbar.LENGTH_SHORT)
                    snackbarLibrary.anchorView = bottomNavigation
                    snackbarLibrary.show()
                    true
                }
                else -> false
            }
        }
    }

    private fun showAppClosingDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Вы хотите выйти?")
            .setMessage("Серьезно??")
            .setPositiveButton("Да") { _, _ -> finish() }
            .setNegativeButton("Нет", null)
            .show()
    }
}


