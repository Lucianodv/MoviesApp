package ui.Movie.adapters.concat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.MovieItemBinding
import core.BaseViewHolder
import data.model.Movie
import data.model.MovieList
import presentation.MovieViewModel

class MovieAdapter(
                  private val movieList: List<Movie>,
                  private val itemClickListener : OnMovieClickListener
                  ) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener{
        fun onMovieClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        //inflamos la vista
       val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MoviesViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            //obtengo la posicion de cada elemnto en el que hago click
            val position = holder.bindingAdapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION}
                        ?: return@setOnClickListener
            itemClickListener.onMovieClick(movieList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MoviesViewHolder -> holder.bind(movieList[position])
        }
    }

    override fun getItemCount(): Int = movieList.size


    private inner class MoviesViewHolder(
        val binding: MovieItemBinding,
        val context: Context,
        ): BaseViewHolder<Movie>(binding.root){
        override fun bind(item: Movie) {
            //Cargamos con glide la imagen del poster con su url base dentro de nuestro imageMovie
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/${item.poster_path}").centerCrop().into(binding.imageMovie)
        }
    }

}
