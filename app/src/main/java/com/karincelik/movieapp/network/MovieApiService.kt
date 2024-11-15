package com.karincelik.movieapp.network

import com.karincelik.movieapp.data.Movie
import com.karincelik.movieapp.data.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiService {

    // API'den film listesini almak için kullanılan GET isteği
    @GET("movie/popular?api_key=fc11bb651d0429176710db914f7d296f ")
    fun getMovies(): Call<List<MovieResponse>>

    // API servisi oluşturma
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        fun create(): MovieApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()

            return retrofit.create(MovieApiService::class.java)
        }
    }
}
