package com.example.moviesk.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesk.R
import com.example.moviesk.data.DataSource
import com.example.moviesk.data.model.Movie
import com.example.moviesk.data.model.MovieDetail
import com.example.moviesk.databinding.FragmentMainBinding
import com.example.moviesk.domain.RepoImpl
import com.example.moviesk.ui.viewmodel.MainViewModel
import com.example.moviesk.ui.viewmodel.VMFactory
import com.example.moviesk.vo.Resource
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainAdapter.OnMovieClickListener {

    private val viewModel by viewModels<MainViewModel> { VMFactory(RepoImpl(DataSource())) }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Acá se infla la vista usando viewBinding
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root//Esta es la raiz de la vista

        binding.refresh.setOnRefreshListener {
        viewModel.refreshMovieList()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        //btn_ir_detalle.setOnClickListener{
        //findNavController().navigate(R.id.moviesDetalleFragment)
        //}k
    }

    private fun setupObservers(){
        viewModel.fetchMoviesList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.refresh.isRefreshing = false
                    binding.progressBar.visibility = View.GONE
                    binding.rvMovies.adapter = MainAdapter(requireContext(), result.data, this)

                }
                is Resource.Failure -> {
                    binding.refresh.isRefreshing = false
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Ocurrió un error ${result.exception}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        })
    }

    private fun setupRecyclerView() {

        binding.rvMovies.layoutManager = GridLayoutManager(
            requireContext(),
            2,
            GridLayoutManager.VERTICAL,
            false
        )
    }

    override fun onMovieClick(movie: Movie) {
        val bundle = Bundle()
        bundle.putString("movie", movie.id)
        //findNavController().navigate(R.id.action_mainFragment_to_moviesDetalleFragment, bundle)
        //navController?.navigateUp()
        navController.navigate(MainFragmentDirections.actionMainFragmentToMoviesDetalleFragment(movie.id))

    }
    suspend fun loadContent(){
        viewModel.refreshMovieList()
    }
}