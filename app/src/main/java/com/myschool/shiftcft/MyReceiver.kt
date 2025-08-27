package com.myschool.shiftcft

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast


class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val isAirplaneModeOn = intent.getBooleanExtra("state", false)
        val isAirplaneModeOnSecond = Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON,
            0
        ) != 0

        if(isAirplaneModeOn){
            Toast.makeText(context, "Включен режим в самолёте", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Выключен режим в самолёте", Toast.LENGTH_SHORT).show()
        }


    }
}