package ui.Movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.PopularMoviesRowBinding
import core.BaseConcatHolder
import data.model.Movie

class PopularConcatAdapter(private val movieAdapter: MovieAdapter)  :RecyclerView.Adapter<BaseConcatHolder<*>>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
       val itemBinding = PopularMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return concatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is concatViewHolder -> holder.bind(movieAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class concatViewHolder (val binding: PopularMoviesRowBinding): BaseConcatHolder<MovieAdapter>(binding.root){
        override fun bind(adapter: MovieAdapter) {
            binding.rvPopularMovies.adapter = adapter
        }
    }


}