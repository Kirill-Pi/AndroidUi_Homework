package com.example.pigolevmyapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pigolevmyapplication.databinding.FragmentDetailsBinding
import com.example.pigolevmyapplication.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoriteFilmsDataBase = mutableListOf<Film>(
        Film(
            "Dr. No",
            R.drawable.drno,
            "A resourceful British government agent seeks answers in a case involving the disappearance of a colleague and the disruption of the American space program."
        ),
        Film(
            "From Russia with Love",
            R.drawable.frwl,
            "James Bond willingly falls into an assassination plot involving a naive Russian beauty in order to retrieve a Soviet encryption device that was stolen by S.P.E.C.T.R.E."
        ),
        Film(
            "Goldfinger",
            R.drawable.gf,
            "While investigating a gold magnate's smuggling, James Bond uncovers a plot to contaminate the Fort Knox gold reserve."
        )
    )


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
        //Получаем список при транзакции фрагмента
        val favoritesList: List<Film> = emptyList()

        binding.favoritesRecycler
            .apply {
                var filmsAdapter =
                    FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                        override fun click(film: Film) {
                            (requireActivity() as MainActivity).launchDetailsFragment(film)
                        }
                    })
                //Присваиваем адаптер
                adapter = filmsAdapter
                //Присвои layoutmanager
                layoutManager = LinearLayoutManager(requireContext())
                //Применяем декоратор для отступов
                val decorator = TopSpacingItemDecoration(8)
                addItemDecoration(decorator)
                filmsAdapter.addItems(favoriteFilmsDataBase)
            }
        //Кладем нашу БД в RV


    }
}