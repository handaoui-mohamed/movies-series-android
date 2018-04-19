package com.handaoui.movies.fakers

import com.handaoui.movies.R
import com.handaoui.movies.data.Person

object Persons {
    var list = arrayListOf<Person>(
            Person(
                    id = 0,
                    name = "James Mangold",
                    bio = "James Mangold is an American film and television director, screenwriter and producer. He is best known for directing the films Cop Land, Girl, Interrupted, Kate & Leopold, Walk the Line, 3:10 to Yuma, and Knight and Day.",
                    picture = R.drawable.james_mangold
            ),
            Person(
                    id = 1,
                    name = "Hugh Jackman",
                    bio = "Hugh Michael Jackman (born 12 October 1968) is an Australian actor, singer, and producer. Jackman has won international recognition for his roles in a variety of film genres. He is known for his long-running role as Wolverine in the X-Men film series, as well as for his lead roles in films such as the romantic-comedy fantasy",
                    picture = R.drawable.hugh_jackman
            ),
            Person(
                    id = 2,
                    name = "Stephen Merchant",
                    bio = "Stephen James Merchant (born 24 November 1974) is an English writer, director, radio presenter, comedian, and actor. Merchant is best known for his collaborations with Ricky Gervais and Karl Pilkington, as the co-writer and co-director of the popular British sitcom The Office (2001–2003), co-writer and co-star of Extras",
                    picture = R.drawable.stephen_merchant
            )
    )

    fun getPersonById(personId:Int) = list.find { person -> person.id == personId }

}