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
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.myschool.shiftcft.databinding.ActivityMainBinding
import com.myschool.shiftcft.fragments.CountDialogFragment
import com.myschool.shiftcft.fragments.UsersFragment
import com.myschool.shiftcft.model.NetworkState
import com.myschool.shiftcft.util.NetworkMonitor
import com.myschool.shiftcft.viewmodel.NetworkViewModel
import kotlinx.coroutines.launch

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
        val networkMonitor = NetworkMonitor(this)

        val viewModel by viewModels<NetworkViewModel> {
            viewModelFactory {
                initializer { NetworkViewModel(networkMonitor) }
            }

        }

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