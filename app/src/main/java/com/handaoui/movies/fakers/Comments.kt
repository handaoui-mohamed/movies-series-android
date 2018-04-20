package com.handaoui.movies.fakers

import com.handaoui.movies.data.Comment


object Comments {
    val movieLists = arrayListOf(
            Comment(
                    commentator = "Bucky Barnes",
                    content = "The original story about Glass as published in The Missouri Trapper ( http://hughglass.org/wp-content/uploads/2015/09/1825-Hugh-Glass-article.pdf ) was far more interesting due to the bleakness of the real events.",
                    rating = 5f,
                    type = "movie",
                    id = 0
            ),
            Comment(
                    commentator = "David Arnold ",
                    content = "Absolute trash\n People do not like this movie because it is good- they like it because other people tell them to like it. It is not a good film. It is one of the worst films I have ever had the pleasure of watching. I spent two hours on a pile of trash- I was forced to watch it, really, I never wanted to- but I went ahead anyway, and I'm glad I watched it, because now I can warn you about it. Do not watch this movie.",
                    rating = 2f,
                    type = "movie",
                    id = 0
            ),
            Comment(
                    commentator = "David Arnold ",
                    content = "Life pits it's will on Hugh Glass, the real-life tracker & fur trapper played excellently by Leonardo DiCaprio. And director Alejandro G. Iñárritu damn near tortures his cast & audience in telling the story of Glass' revenge against the people, headed by John Fitzgerald (Tom Hardy), who done him wrong. Set in the untamed West of 1823, this is one brutal epic piece of film making.",
                    rating = 8f,
                    type = "movie",
                    id = 0
            )
    )

    fun getMovieComments(movieId: Int) = movieLists.filter { comment -> comment.type == "movie" && comment.id == movieId }
}