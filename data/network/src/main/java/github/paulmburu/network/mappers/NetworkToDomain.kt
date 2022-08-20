package github.paulmburu.network.mappers

import github.paulmburu.domain.models.Follower
import github.paulmburu.domain.models.Following
import github.paulmburu.domain.models.Repo
import github.paulmburu.domain.models.User
import github.paulmburu.network.models.*

fun UserDto.toDomain(): User {
    return User(
        id = id,
        login = login,
        name = name,
        bio = bio,
        avatarUrl = avatarUrl,
        company = company,
        location = location,
        repos = repos,
        followers = followers,
        following = following
    )
}


fun RepoDto.toDomain(): Repo {
    return Repo(id = id, name = name, description = description, stargazersCount = stargazersCount)
}

fun FollowerDto.toDomain(): Follower {
    return Follower(id = id, login = login, avatarUrl =  avatarUrl)
}

fun FollowingDto.toDomain(): Following {
    return Following(id = id, login = login, avatarUrl =  avatarUrl)
}