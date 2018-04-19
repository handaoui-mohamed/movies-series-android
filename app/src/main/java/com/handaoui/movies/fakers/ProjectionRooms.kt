package com.handaoui.movies.fakers

import com.handaoui.movies.R
import com.handaoui.movies.data.ProjectionRoom


object ProjectionRooms {
    var list: ArrayList<ProjectionRoom> = arrayListOf(
            ProjectionRoom(
                    id = 0,
                    image = R.drawable.the_revenant,
                    name = "Salle Ibn Khaldoun",
                    address = "12, Dr Ch.Saadane St"
            ),
            ProjectionRoom(
                    id = 0,
                    image = R.drawable.the_revenant,
                    name = "Salle Ibn Zaydoun",
                    address = "El Madania"),
            ProjectionRoom(
                    id = 0,
                    image = R.drawable.the_revenant,
                    name = "Cinéma Atlas",
                    address = "52 Rue Didouche Mourad, Alger 16000"),
            ProjectionRoom(
                    id = 0,
                    image = R.drawable.the_revenant,
                    name = "El Mouggar",
                    address = "Rue Asselah Hocine, Alger 16000"),
            ProjectionRoom(
                    id = 0,
                    image = R.drawable.the_revenant,
                    name = "Salle Echabab (Ex Casino)",
                    address = "Ctre, Alger Ctre"),
            ProjectionRoom(
                    id = 0,
                    image = R.drawable.the_revenant,
                    name = "Cinéma AFRIQUE",
                    address = "Rue des Frères Meslem, Sidi M'Hamed 16000"),
            ProjectionRoom(
                    id = 0,
                    image = R.drawable.the_revenant,
                    name = "Cinéma Metidja",
                    address = "Alger Ctre 16000")
    )
}