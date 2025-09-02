package com.myschool.shiftcft

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class TimeZoneReceiver : BroadcastReceiver() {

    var timeChanged: Boolean = false
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "TIME ZONE CHANGED!!!", Toast.LENGTH_LONG).show()
        Log.d("TimeZoneReceiver", "Received intent: ${intent.action}")

        if (intent.action == Intent.ACTION_TIMEZONE_CHANGED) {
            Toast.makeText(context, "TIME ZONE CHANGED!!!", Toast.LENGTH_LONG).show()
            Log.d("TimeZoneReceiver", "Time zone changed detected")
        }
    }
}