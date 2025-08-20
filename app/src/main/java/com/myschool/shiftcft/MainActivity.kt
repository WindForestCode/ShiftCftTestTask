package com.myschool.shiftcft

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.myschool.shiftcft.databinding.ActivityMainBinding
import com.myschool.shiftcft.fragments.CountDialogFragment

import com.myschool.shiftcft.fragments.UsersFragment

class MainActivity : AppCompatActivity(), CountDialogFragment.CountInputListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, UsersFragment(), "UsersFragment").commit()
        }

    }

    override fun onCountInput(count: Int) {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment is UsersFragment) {
            Log.d("onCount", "onCount true count = $count")
            fragment.onCountInput(count)
        }
    }

}