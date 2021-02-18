package com.example.moviesk.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviesk.R
import com.example.moviesk.data.DataSource
import com.example.moviesk.data.model.Genre
import com.example.moviesk.data.model.Movie
import com.example.moviesk.data.model.MovieDetail
import com.example.moviesk.databinding.FragmentMoviesDetalleBinding
import com.example.moviesk.domain.RepoImpl
import com.example.moviesk.ui.viewmodel.MainViewModel
import com.example.moviesk.ui.viewmodel.VMFactory
import com.example.moviesk.vo.Resource
import kotlinx.android.synthetic.main.movies_item_span.view.*

class MoviesDetalleFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel> { VMFactory(RepoImpl(DataSource())) }

    private var _binding: FragmentMoviesDetalleBinding? = null
    private val binding get() = _binding!!

    //private lateinit var movie: String

    private lateinit var movieDetail: MovieDetail

    private val args by navArgs<MoviesDetalleFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            //movie = it.getString("movie")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesDetalleBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel.setMovieIdData(args.id)

        viewModel.fetchMovieDetail.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar2.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    movieDetail = result.data
                    val baseurlimg2 = "https://image.tmdb.org/t/p"
                    val sizeimg2 = "/w500/"
                    Glide.with(requireContext()).load(baseurlimg2 + sizeimg2 + movieDetail.imagenDetail).into(binding.ivImagenDetail)
                    binding.tvNombreDetail.text = movieDetail.nombreDetail
                    binding.tvDuracionDetail.text = movieDetail.duracionDetail
                    binding.tvFestrenoDetail.text = movieDetail.festrenoDetail
                    binding.tvCalificacionDetail.text = movieDetail.ratingDetail
                    getGenerosXD()
                    binding.tvDescripcionDetail.text = movieDetail.descripcionDetail
                    binding.progressBar2.visibility = View.GONE
                }
                is Resource.Failure -> {
                    binding.progressBar2.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Ocurrió un error ${result.exception}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        })


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun getGenerosXD(){
        //(index, value) in array.withIndex()
        for ((index, value) in movieDetail.genresDetailList.withIndex().iterator()){
            binding.tvGenerosDetail.text = movieDetail.genresDetailList[index].genreNameElement
        }
    }

}