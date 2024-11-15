package com.karincelik.movieapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karincelik.movieapp.data.Movie
import com.karincelik.movieapp.data.MovieResponse
import com.karincelik.movieapp.network.MovieApiService
import com.karincelik.movieapp.ui.MovieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView ve Adapter Ayarları
        recyclerView = findViewById(R.id.recyclerView)  // findViewById ile RecyclerView'i bağlanıyor
        movieAdapter = MovieAdapter(listOf())  // Adapter'in başlangıç verisi boş
        recyclerView.layoutManager = LinearLayoutManager(this)  // LinearLayoutManager ile listeyi düzenliyor
        recyclerView.adapter = movieAdapter

        // API'den Verileri Çek
        fetchMovies()
    }

    private fun fetchMovies() {
        val movieApiService = MovieApiService.create()  // API servisini oluştu
        val call = movieApiService.getMovies()  // API'den verileri çekmek için çağrı oluştu

        // API çağrısı
        call.enqueue(object : Callback<MovieResponse> {  // Callback türünü doğru olarak kullanalım
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    // API'den dönen veri (MovieResponse) işleniyor
                    response.body()?.results?.let { movies ->
                        movieAdapter.updateMovies(movies)  // Adapter'a yeni verileri gönderiyoruz
                        Log.d("MainActivity", "Movies fetched successfully: ${movies.size} movies")
                    }
                } else {
                    Log.e("MainActivity", "Failed to fetch movies")  // Hata durumunda loglama
                    Toast.makeText(this@MainActivity, "Failed to fetch movies", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

private fun <T> Call<T>.enqueue(callback: Callback<MovieResponse>) {

}
