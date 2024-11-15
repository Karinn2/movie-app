package com.karincelik.movieapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    // Retrofit nesnesini döndüren fonksiyon
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())  // JSON dönüşüm için Gson kullanıyoruz
            .build()
    }

    // API servisinin örneğini döndüren fonksiyon
    fun getMovieApiService(): MovieApiService {
        return getRetrofitInstance().create(MovieApiService::class.java)
    }
}
