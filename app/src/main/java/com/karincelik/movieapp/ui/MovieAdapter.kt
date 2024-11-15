package com.karincelik.movieapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karincelik.movieapp.R
import com.karincelik.movieapp.data.Movie

class MovieAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    // ViewHolder sınıfı: Tek bir film öğesini temsil eder
    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitle: TextView = view.findViewById(R.id.movieTitle)
        val movieReleaseDate: TextView = view.findViewById(R.id.movieYear)
        val moviePoster: ImageView = view.findViewById(R.id.moviePoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        // Film başlığını ve çıkış tarihini ayarlama
        holder.movieTitle.text = movie.title
        holder.movieReleaseDate.text = movie.release_date

        // Glide kullanarak film posterini yükleme
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
            .into(holder.moviePoster)
    }

    override fun getItemCount() = movies.size

    // Verileri güncelleme fonksiyonu
    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()  // Adapter'ın verilerinin güncellenmesi gerektiğini RecyclerView'a iletiyor
    }
}
