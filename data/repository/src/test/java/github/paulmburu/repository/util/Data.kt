package github.paulmburu.repository.util

import github.paulmburu.local.models.FollowerEntity
import github.paulmburu.local.models.FollowingEntity
import github.paulmburu.local.models.RepoEntity
import github.paulmburu.local.models.UserEntity
import github.paulmburu.network.models.FollowerDto
import github.paulmburu.network.models.FollowingDto
import github.paulmburu.network.models.RepoDto
import github.paulmburu.network.models.UserDto

object Data {
    val query = "Paulmburu"

    val userResponse = UserEntity(
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

    val     userQueryResponse = UserDto(
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

    val reposResponse = listOf(
        RepoEntity(
            id = 227655606,
            name = "algs4",
            description = "Algorithms, 4th edition textbook code and libraries",
            stargazersCount = 4,
        )
    )

    val reposQueryResponse = listOf(
        RepoDto(
            id = 227655606,
            name = "algs4",
            description = "Algorithms, 4th edition textbook code and libraries",
            stargazersCount = 4,
        )
    )

    val followersResponse = listOf(
        FollowerEntity(
            id = 24954467,
            login = "JohnDoe",
            avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4"
        )
    )


    val followersQueryResponse = listOf(
        FollowerDto(
            id = 24954467,
            login = "JohnDoe",
            avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4"
        )
    )

    val followingResponse = listOf(
        FollowingEntity(
            id = 24954467,
            login = "JohnDoe",
            avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4"
        )
    )


    val followingQueryResponse = listOf(
        FollowingDto(
            id = 24954467,
            login = "JohnDoe",
            avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4"
        )
    )
}