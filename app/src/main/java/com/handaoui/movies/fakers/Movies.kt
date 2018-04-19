package com.handaoui.movies.fakers

import com.handaoui.movies.R
import com.handaoui.movies.data.Movie
import java.util.*

object Movies {
    private var projectRooms = ProjectionRooms.list
    private var persons = Persons.list

    var list: ArrayList<Movie> = arrayListOf(
            Movie(
                    id = 0,
                    title = "The Revenant",
                    cover = R.drawable.the_revenant,
                    description = "While exploring uncharted wilderness in 1823, legendary frontiersman Hugh Glass sustains injuries from a brutal bear attack. When his hunting team leaves him for dead, Glass must utilize his survival skills to find a way back home while avoiding natives on their own hunt. Grief-stricken and fueled by vengeance, Glass treks through the wintry terrain to track down John Fitzgerald, the former confidant who betrayed and abandoned him.",
                    rating = 8f,
                    genre = listOf("Action", "Adventure", "Drama", "History", "Thriller", "Western"),
                    projectRoom = projectRooms[0],
                    date = "01/01/2014",
                    actors = persons,
                    directors = persons
            ),
            Movie(
                    id = 1,
                    title = "Logan",
                    cover = R.drawable.logan,
                    description = "In 2029 the mutant population has shrunken significantly due to genetically modified plants designed to reduce mutant powers and the X-Men have disbanded. Logan, whose power to self-heal is dwindling, has surrendered himself to alcohol and now earns a living as a chauffeur. He takes care of the ailing old Professor X whom he keeps hidden away. One day, a female stranger asks Logan to drive a girl named Laura to the Canadian border. At first he refuses, but the Professor has been waiting for a long time for her to appear. Laura possesses an extraordinary fighting prowess and is in many ways like Wolverine. She is pursued by sinister figures working for a powerful corporation; this is because they made her, with Logan's DNA. A decrepit Logan is forced to ask himself if he can or even wants to put his remaining powers to good use. It would appear that in the near-future, the times in which they were able put the world to rights with razor sharp claws and telepathic powers are now over.",
                    rating = 8.1f,
                    genre = listOf("Action", "Drama ", "Sci-Fi", "Thriller"),
                    projectRoom = projectRooms[2],
                    date = "01/01/2017",
                    actors = persons,
                    directors = persons
            ),
            Movie(
                    id = 2,
                    title = "The Fate of the Furious",
                    cover = R.drawable.the_fate_of_the_furious,
                    description = "Now that Dom and Letty are on their honeymoon and Brian and Mia have retired from the game-and the rest of the crew has been exonerated-the globetrotting team has found a semblance of a normal life. But when a mysterious woman seduces Dom into the world of crime he can't seem to escape and a betrayal of those closest to him, they will face trials that will test them as never before. From the shores of Cuba and the streets of New York City to the icy plains off the arctic Barents Sea, the elite force will crisscross the globe to stop an anarchist from unleashing chaos on the world's stage... and to bring home the man who made them a family.",
                    rating = 6.7f,
                    genre = listOf("Action", "Adventure", "Crime ", "Thriller"),
                    projectRoom = projectRooms[4],
                    date = "01/01/2017",
                    actors = persons,
                    directors = persons
            ),
            Movie(
                    id = 3,
                    title = "Spider-Man: Homecoming",
                    cover = R.drawable.spiderman_homecoming,
                    description = "Thrilled by his experience with the Avengers, Peter returns home, where he lives with his Aunt May, under the watchful eye of his new mentor Tony Stark, Peter tries to fall back into his normal daily routine - distracted by thoughts of proving himself to be more than just your friendly neighborhood Spider-Man - but when the Vulture emerges as a new villain, everything that Peter holds most important will be threatened.",
                    rating = 7.5f,
                    genre = listOf("Action", "Adventure", "Sci-Fi"),
                    projectRoom = projectRooms[3],
                    date = "01/01/2017",
                    actors = persons,
                    directors = persons
            )
    )

    fun getProjectedMovies(): ArrayList<Movie> {
        val filtered = ArrayList<Movie>()
        list.forEach { movie -> if (movie.projectRoom !== null) filtered.add(movie) }
        return filtered
    }

    fun getMovieById(id: Int): Movie? = list.find { movie -> movie.id == id }
}