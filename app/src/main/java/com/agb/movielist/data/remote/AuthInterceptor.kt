package com.agb.movielist.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return makeRequest(chain)
    }

    private fun makeRequest(
        chain: Interceptor.Chain,
        accessToken: String? = ACCESS_TOKEN
    ): Response {
        val oldRequest = chain
            .request()
            .newBuilder()
            .addHeader(AUTHORIZATION, "Bearer $accessToken")
            .addHeader(API_KEY, API_KEY_VALUE)
            .build()

        return chain.proceed(oldRequest)
    }


    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API_KEY = "Accept"
        private const val API_KEY_VALUE = "application/json"
        private const val AUTHORIZATION = "Authorization"
        private const val ACCESS_TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3YWQ2OTZkNTA5MWYwODljZDg0YjIwZGE4Nzc4OTJmOSIsIm5iZiI6MTcxOTkxMzE3OS40MzQ3NDEsInN1YiI6IjY2ODNjOGM0YWZkYWRmZjBjZTliMjI5MSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Rw0zaNbZcZAuV7OA2RD1dr6S4A9OEB_AL3CIN-TgFVY"
    }
}