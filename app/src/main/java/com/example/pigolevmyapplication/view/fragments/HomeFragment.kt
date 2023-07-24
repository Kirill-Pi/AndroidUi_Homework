package com.example.pigolevmyapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView.*
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pigolevmyapplication.App.Companion.instance
import com.example.pigolevmyapplication.databinding.FragmentHomeBinding
import com.example.pigolevmyapplication.domain.Film
import com.example.pigolevmyapplication.domain.Interactor
import com.example.pigolevmyapplication.utils.AnimationHelper
import com.example.pigolevmyapplication.view.rv_adapters.FilmListRecyclerAdapter
import com.example.pigolevmyapplication.view.MainActivity
import com.example.pigolevmyapplication.view.rv_adapters.TopSpacingItemDecoration
import com.example.pigolevmyapplication.viewmodel.HomeFragmentViewModel
import java.util.*



class HomeFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }
    private lateinit var binding: FragmentHomeBinding

    private lateinit var filmsAdapter: FilmListRecyclerAdapter



    private var filmsDataBase = listOf<Film>()
        //Используем backing field
        set(value) {
            //Если придет такое же значение, то мы выходим из метода
            if (field == value) return
            //Если пришло другое значение, то кладем его в переменную
            field = value
            //Обновляем RV адаптер
            filmsAdapter.updateItems(field.toMutableList())
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        binding.mainRecycler.apply {
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {

                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)

            val filmObserver = Observer<List<Film>> {
                filmsDataBase = it
            }
            viewModel.filmsListLiveData.observe(viewLifecycleOwner, filmObserver)

        }

        searchViewInit(binding)
    //Setup searchView depending on scroll direction
        binding.mainRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                binding.searchView.isVisible = dy >= 0

                }
            }
        )
        AnimationHelper.performFragmentCircularRevealAnimation(binding.homeFragmentRoot, requireActivity(), 1)
    }

    private fun searchViewInit(binding: FragmentHomeBinding) {
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isEmpty() == true) {
                    filmsAdapter.updateItems(filmsDataBase.toMutableList())
                    return true
                }
                val result = filmsDataBase.filter {

                    it.title.lowercase(Locale.getDefault()).contains(
                        newText?.lowercase(Locale.getDefault())!!
                    )
                }
                filmsAdapter.updateItems(result.toMutableList())
                return true
            }
        })
    }
}













