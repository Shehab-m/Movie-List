package com.agb.movielist

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.agb.movielist.data.service.MoviesWorker
import com.agb.movielist.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lifecycleOwner: LifecycleOwner = this
        initWorkManager(lifecycleOwner)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpAppBarConfigurations()
    }

    private fun initWorkManager(lifecycleOwner: LifecycleOwner) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<MoviesWorker>(
            repeatInterval = 4,
            repeatIntervalTimeUnit = TimeUnit.HOURS
        ).setBackoffCriteria(
            backoffPolicy = BackoffPolicy.LINEAR,
            duration = Duration.ofSeconds(15)
        ).setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(applicationContext)
        workManager.enqueueUniquePeriodicWork(
            MOVIES_WORK,
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            workRequest
        )
        workManager.getWorkInfosForUniqueWorkLiveData(MOVIES_WORK)
            .observe(lifecycleOwner) {
                it.forEach { workInfo ->
                    Log.d("MainActivity: ", "${workInfo.state}")
                }
            }
    }

    private fun setUpAppBarConfigurations() {
        setSupportActionBar(binding.toolbar)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val controller = navHostFragment.navController

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        appBarConfiguration = AppBarConfiguration(controller.graph)
        setupActionBarWithNavController(controller, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView2)
        return navController.navigateUp(appBarConfiguration)
    }

    companion object {
        const val MOVIES_WORK = "MoviesWork"
    }

}