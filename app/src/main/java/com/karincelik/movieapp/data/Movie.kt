package com.karincelik.movieapp.data

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
data class Movie(
    val id: Int,
    val title: String,
    val release_date: String,
    val poster_path: String
){
    // Görselin URL'sini döndüren fonksiyon
    fun getPosterUrl(): String {
        return IMAGE_BASE_URL + poster_path
    }
}