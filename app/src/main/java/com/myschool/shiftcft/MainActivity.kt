package com.myschool.shiftcft

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.myschool.shiftcft.databinding.ActivityMainBinding
import com.myschool.shiftcft.presentation.fragments.CountDialogFragment
import com.myschool.shiftcft.presentation.fragments.UsersFragment
import com.myschool.shiftcft.util.NetworkState
import com.myschool.shiftcft.presentation.receiver.MyReceiver
import com.myschool.shiftcft.presentation.receiver.TimeZoneReceiver
import com.myschool.shiftcft.presentation.viewmodel.NetworkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CountDialogFragment.CountInputListener {

    private val airplaneModeReceiver = MyReceiver()
    private val timeZoneReceiver = TimeZoneReceiver()

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

        val viewModel: NetworkViewModel by viewModels()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, UsersFragment(), "UsersFragment").commit()
        }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.networkState.collect { state ->
                        when(state) {
                            is NetworkState.Connected -> {}
                            is NetworkState.Disconnected -> {
                                showToast(getString(R.string.network_unavailable))
                            }
                            is NetworkState.Restored -> {
                                showToast(getString(R.string.network_restored))
                            }
                        }
                    }
                }
            }


    }

    override fun onStart() {
        registerReceiver(airplaneModeReceiver, IntentFilter("android.intent.action.AIRPLANE_MODE"))
        if (timeZoneReceiver.timeChanged) {
            Toast.makeText(this, "TIME ZONE CHANGED!!!", Toast.LENGTH_LONG).show()
        }
        super.onStart()
    }

    fun showToast(text: String) {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onCountInput(count: Int) {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment is UsersFragment) {
            Log.d("onCount", "onCount true count = $count")
            fragment.onCountInput(count)
        }
    }

    override fun onDestroy() {
        unregisterReceiver(airplaneModeReceiver)
        super.onDestroy()
    }
}