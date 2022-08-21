package github.paulmburu.domain.fakes

import github.paulmburu.domain.models.Follower
import github.paulmburu.domain.models.Following
import github.paulmburu.domain.models.Repo
import github.paulmburu.domain.models.User

object Data {
    val user = User(
        id = 24954467,
        login = "Paulmburu",
        name = "Paul Mburu",
        bio = "Software Engineer || Tech Enthusiast\\r\\n",
        avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
        company = null,
        location = "Nairobi, Kenya",
        repos = 94,
        followers = 134,
        following = 215
    )

    object Followers {
        val response = arrayListOf(
            Follower(
                id = 24954467,
                login = "JohnDoe",
                avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
            ),
            Follower(
                id = 24954468,
                login = "JaneDoe",
                avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
            ),
            Follower(
                id = 24954469,
                login = "JimmyDoe",
                avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
            ),
        )
    }

    object FollowingData {
        val response = arrayListOf(
            Following(
                id = 24954467,
                login = "JohnDoe",
                avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
            ),
            Following(
                id = 24954468,
                login = "JaneDoe",
                avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
            ),
            Following(
                id = 24954469,
                login = "JimmyDoe",
                avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
            ),
        )
    }


    object Repos {
        val response = arrayListOf(
            Repo(
                id = 227655605,
                name = "30-Days-of-Code-1",
                description = "30 Days of Code by HackerRank Solutions in C++, C#, F#, Go, Java, Python, Ruby, Swift & TypeScript. PRs Welcome!",
                stargazersCount = 1
            ),
            Repo(
                id = 227655606,
                name = "algs4",
                description = "Algorithms, 4th edition textbook code and libraries",
                stargazersCount = 4
            ),
            Repo(
                id = 227655607,
                name = "andfun-kotlin-mars-real-estate",
                description = "NASA photos with a whimsical theme demonstrate how to work with web services on Android.",
                stargazersCount = 8
            ),
        )
    }
}