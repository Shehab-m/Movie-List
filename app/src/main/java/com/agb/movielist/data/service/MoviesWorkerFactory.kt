package com.agb.movielist.data.service

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.agb.movielist.domain.usecase.InvalidateCachedMoviesUseCase
import javax.inject.Inject

class MoviesWorkerFactory @Inject constructor(
    private val invalidateCachedMovies: InvalidateCachedMoviesUseCase
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker = MoviesWorker(invalidateCachedMovies, appContext, workerParameters)
}