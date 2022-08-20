package github.paulmburu.local.mappers

import github.paulmburu.domain.models.Follower
import github.paulmburu.domain.models.Following
import github.paulmburu.domain.models.Repo
import github.paulmburu.domain.models.User
import github.paulmburu.local.models.FollowerEntity
import github.paulmburu.local.models.FollowingEntity
import github.paulmburu.local.models.RepoEntity
import github.paulmburu.local.models.UserEntity

fun User.toLocal(): UserEntity {
    return UserEntity(
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

fun Follower.toLocal(): FollowerEntity {
    return FollowerEntity(id = id, login = login, avatarUrl = avatarUrl)
}

fun Following.toLocal(): FollowingEntity {
    return FollowingEntity(id = id, login = login, avatarUrl = avatarUrl)
}

fun Repo.toLocal(): RepoEntity {
    return RepoEntity(
        id = id,
        name = name,
        description = description,
        stargazersCount = stargazersCount
    )
}