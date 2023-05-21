package com.example.pigolevmyapplication

class FilmDB {

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

    private val favorites = mutableListOf<Film>()
    private fun getFavorites (){
        filmsDataBase.forEach {
            if (it.isInFavorites){
                favorites.add(it)
            }

        }

    }



}

