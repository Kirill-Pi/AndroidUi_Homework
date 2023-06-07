package com.example.pigolevmyapplication

import android.os.Bundle
import android.transition.Fade
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pigolevmyapplication.databinding.FragmentDetailsBinding
import com.example.pigolevmyapplication.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {

    //Fragment animation setup
    init {
        enterTransition = Fade(Fade.IN).apply { duration = 800 }
        returnTransition = Fade(Fade.OUT).apply {
            duration = 800;mode = Fade.MODE_OUT
        }
    }

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private var favoritesDB = FilmDB.favoritesDB()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoritesRecycler
            .apply {
                var filmsAdapter =
                    FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                        override fun click(film: Film) {
                            (requireActivity() as MainActivity).launchDetailsFragment(film)
                        }
                    })
                adapter = filmsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                val decorator = TopSpacingItemDecoration(8)
                addItemDecoration(decorator)
                filmsAdapter.addItems(favoritesDB)
            }
    }
}