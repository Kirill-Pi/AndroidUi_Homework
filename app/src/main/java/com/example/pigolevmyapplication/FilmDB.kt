package com.example.pigolevmyapplication


class FilmDB  {
    companion object {
        val filmsDataBase = mutableListOf<Film>(

            Film(
                "Dr. No",
                R.drawable.drno,
                "A resourceful British government agent seeks answers in a case involving the disappearance of a colleague and the disruption of the American space program.",
                7.2f
            ),
            Film(
                "From Russia with Love",
                R.drawable.frwl,
                "James Bond willingly falls into an assassination plot involving a naive Russian beauty in order to retrieve a Soviet encryption device that was stolen by S.P.E.C.T.R.E.",
                7.0f
            ),
            Film(
                "Goldfinger",
                R.drawable.gf,
                "While investigating a gold magnate's smuggling, James Bond uncovers a plot to contaminate the Fort Knox gold reserve.",
                7.7f
            ),
            Film(
                "Thunderball",
                R.drawable.tb,
                "James Bond heads to the Bahamas to recover two nuclear warheads stolen by S.P.E.C.T.R.E. Agent Emilio Largo in an international extortion scheme.",
                8.0f
            ),
            Film(
                "You Only Live Twice",
                R.drawable.yolt,
                "James Bond and the Japanese Secret Service must find and stop the true culprit of a series of space hijackings, before war is provoked between Russia and the United States.",
                7.3f
                ),
            Film(
                "Live and Let Die",
                R.drawable.lald,
                "James Bond is sent to stop a diabolically brilliant heroin magnate armed with a complex organisation and a reliable psychic tarot card reader.",
            5.0f
            ),
            Film(
                "Moonraker",
                R.drawable.mr,
                "James Bond investigates the mid-air theft of a space shuttle, and discovers a plot to commit global genocide.",
                5.4f
                ),
            Film(
                " Octopussy",
                R.drawable.op,
                "A fake Fabergé egg, and a fellow Agent's death, lead James Bond to uncover an international jewel-smuggling operation, headed by the mysterious Octopussy, being used to disguise a nuclear attack on N.A.T.O. forces.",
                3.5f
            ),
            Film(
                "The Living Daylights",
                R.drawable.tld,
                "James Bond is sent to investigate a KGB policy to kill all enemy spies and uncovers an arms deal that potentially has major global ramifications.",
                8.0f
            ),
            Film(
                "Tomorrow Never Dies",
                R.drawable.tnd,
                "James Bond sets out to stop a media mogul's plan to induce war between China and the UK in order to obtain exclusive global media coverage.",
                7.5f
            ),
            )

            fun favoritesDB () : MutableList<Film> {
            var temp = mutableListOf<Film>()
            temp.clear()
            filmsDataBase.forEach {
                if (it.isInFavorites) temp.add(it)
            }
            return temp
        }
    }
}






