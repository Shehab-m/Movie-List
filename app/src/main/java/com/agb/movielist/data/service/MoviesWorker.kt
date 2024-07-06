package com.agb.movielist.data.service

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.agb.movielist.domain.usecase.InvalidateCachedMoviesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.net.UnknownHostException

@HiltWorker
class MoviesWorker @AssistedInject constructor(
    private val invalidateCache: InvalidateCachedMoviesUseCase,
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            Log.d("MoviesWorker: ", "Success!")
            val isDoneCorrectly = invalidateCache()
            Log.d("MoviesWorker: ", "isDoneCorrectly: $isDoneCorrectly")
            Result.success()
        } catch (e: Exception) {
            if (e is UnknownHostException) {
                Log.d("MoviesWorker: ", "Retrying...")
                Result.retry()
            } else {
                Log.d("MoviesWorker: ", "Error!")
                Result.failure(Data.Builder().putString("error", e.toString()).build())
            }
        }
    }
}

