package com.agb.movielist

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import com.agb.movielist.data.service.MoviesWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MovieApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: MoviesWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(workerFactory)
            .build()

}