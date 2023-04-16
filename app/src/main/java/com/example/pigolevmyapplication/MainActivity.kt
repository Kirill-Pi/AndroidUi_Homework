package com.example.pigolevmyapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun OnClickToast1 (view: View) {
        Toast.makeText(this, "Поиск", Toast.LENGTH_SHORT).show()
    }
    fun OnClickToast2 (view: View) {
        Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
    }
    fun OnClickToast3 (view: View) {
        Toast.makeText(this, "Отложить", Toast.LENGTH_SHORT).show()
    }
    fun OnClickToast4 (view: View) {
        Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
    }
    fun OnClickToast5 (view: View) {
        Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
    }
}

