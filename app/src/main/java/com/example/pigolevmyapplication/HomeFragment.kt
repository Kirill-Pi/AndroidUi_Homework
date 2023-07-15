package com.example.pigolevmyapplication

import android.os.Bundle
import android.transition.*
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pigolevmyapplication.databinding.FragmentHomeBinding

import com.example.pigolevmyapplication.databinding.MergeHomeScreenContentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

import java.util.*


//@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    //transition setup
    init {
        exitTransition = Fade(Fade.OUT).apply { duration = 800;mode = Fade.MODE_OUT }
        reenterTransition = Fade(Fade.IN).apply { duration = 800; }
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var binding2: MergeHomeScreenContentBinding


    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private var filmDB = FilmDB.filmsDataBase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view=binding.root

        binding2 = MergeHomeScreenContentBinding.inflate(layoutInflater, binding.homeFragmentRoot, false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        binding.mainRecycler.apply {

        binding2.mainRecycler.apply {

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
            filmsAdapter.updateItems(filmDB)
//Scene animation setup
            val scene = Scene(binding.homeFragmentRoot, binding2.root)
            val searchSlide = Slide(Gravity.TOP).addTarget(binding2.searchView)
            val recyclerSlide = Slide(Gravity.BOTTOM).addTarget(binding2.mainRecycler)
            val customTransition = TransitionSet().apply {
                duration = 500
                addTransition(recyclerSlide)
                addTransition(searchSlide)
            }
            TransitionManager.go(scene, customTransition)
        }


        searchViewInit(binding)
    //Setup searchView depending on scroll direction
        binding.mainRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                binding.searchView.isVisible = dy >= 0

        searchViewInit(binding2)
        /*
        //Setup searchView depending on scroll direction
        binding2.mainRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
           override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
               binding2.searchView.isVisible = dy >= 0


                }
            }
        )
        AnimationHelper.performFragmentCircularRevealAnimation(binding.homeFragmentRoot, requireActivity(), 1)

           }
        }
        )*/

    }

    private fun searchViewInit(binding: MergeHomeScreenContentBinding) {
        binding2.searchView.setOnClickListener {
            binding2.searchView.isIconified = false
        }
        binding2.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isEmpty() == true) {
                    filmsAdapter.updateItems(filmDB)
                    return true
                }
                val result = filmDB.filter {

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











