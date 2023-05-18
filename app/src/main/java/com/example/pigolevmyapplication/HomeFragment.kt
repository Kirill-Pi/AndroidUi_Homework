package com.example.pigolevmyapplication

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pigolevmyapplication.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private val filmsDataBase = mutableListOf<Film>(
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
        ),
        Film(
            "Thunderball",
            R.drawable.tb,
            "James Bond heads to the Bahamas to recover two nuclear warheads stolen by S.P.E.C.T.R.E. Agent Emilio Largo in an international extortion scheme."
        ),
        Film(
            "You Only Live Twice",
            R.drawable.yolt,
            "James Bond and the Japanese Secret Service must find and stop the true culprit of a series of space hijackings, before war is provoked between Russia and the United States."
        ),
        Film(
            "Live and Let Die",
            R.drawable.lald,
            "James Bond is sent to stop a diabolically brilliant heroin magnate armed with a complex organisation and a reliable psychic tarot card reader."
        ),
        Film(
            "Moonraker",
            R.drawable.mr,
            "James Bond investigates the mid-air theft of a space shuttle, and discovers a plot to commit global genocide."
        ),
        Film(
            " Octopussy",
            R.drawable.op,
            "A fake Fabergé egg, and a fellow Agent's death, lead James Bond to uncover an international jewel-smuggling operation, headed by the mysterious Octopussy, being used to disguise a nuclear attack on N.A.T.O. forces."
        ),
        Film(
            "The Living Daylights",
            R.drawable.tld,
            "James Bond is sent to investigate a KGB policy to kill all enemy spies and uncovers an arms deal that potentially has major global ramifications."
        ),
        Film(
            "Tomorrow Never Dies",
            R.drawable.tnd,
            "James Bond sets out to stop a media mogul's plan to induce war between China and the UK in order to obtain exclusive global media coverage."
        ),

        )

    private lateinit var filmsAdapter: FilmListRecyclerAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view=binding.root
        return view
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
            filmsAdapter.updateItems(filmsDataBase)
        }
    }



}









