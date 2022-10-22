package ui.Movie

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMovieBinding
import data.model.Movie
import data.model.MovieList
import data.remote.MovieDataSource
import presentation.MovieViewModel
import presentation.MovieViewModelFactory
import repository.MovieRepositoryImplement
import repository.RetrofitClient
import repository.WebService
import ui.Movie.adapters.concat.MovieAdapter
import ui.Movie.adapters.concat.PopularConcatAdapter
import ui.Movie.adapters.concat.TopRatedConcatAdapter
import ui.Movie.adapters.concat.UpComingMoviesConcatAdapter
import kotlin.math.log


class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener{

    private lateinit var binding: FragmentMovieBinding

    private val viewModel by viewModels<MovieViewModel> { MovieViewModelFactory(MovieRepositoryImplement(MovieDataSource(RetrofitClient.webservice) )) }


private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is core.Resources.Loading -> {
                    Log.d("LiveData", "Loading...")
                    binding.progressBar.visibility = View.VISIBLE
                }

                is core.Resources.succed -> {
                    binding.progressBar.visibility = View.GONE
                concatAdapter.apply {
                  addAdapter(0, UpComingMoviesConcatAdapter(MovieAdapter(result.data.first.results, this@MovieFragment)))
                    addAdapter(1, TopRatedConcatAdapter(MovieAdapter(result.data.second.results, this@MovieFragment)))
                    addAdapter(2, PopularConcatAdapter(MovieAdapter(result.data.third.results, this@MovieFragment)))

                }
                    binding.rvMovies.adapter = concatAdapter
                }

                is core.Resources.failure ->{
                    Log.d("Error","${result.exception}")
                    binding.progressBar.visibility = View.GONE
                }

            }
        })

    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date)
        findNavController().navigate(action)
    }
}