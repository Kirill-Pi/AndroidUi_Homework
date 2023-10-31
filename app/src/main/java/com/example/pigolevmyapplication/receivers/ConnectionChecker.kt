package com.example.pigolevmyapplication.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate

class ConnectionChecker : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        //Если интента нет то выходим из метода
        if (intent == null) return
        //Проверяем какой пришел action
        when (intent.action) {
            //Если пришел низкий заряд батарее
            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(context, "Батарея разряжена", Toast.LENGTH_SHORT).show()
                //Устанавливаем ночную тему
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            //Если пришло подключение к зараядке
            Intent.ACTION_POWER_CONNECTED -> {
                Toast.makeText(context, "Зарядка подключена", Toast.LENGTH_SHORT).show()
                //Устанавливаем дневную тему
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }


}