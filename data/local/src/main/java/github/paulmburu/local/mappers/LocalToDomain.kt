package github.paulmburu.local.mappers

import github.paulmburu.domain.models.Follower
import github.paulmburu.domain.models.Following
import github.paulmburu.domain.models.Repo
import github.paulmburu.domain.models.User
import github.paulmburu.local.models.FollowerEntity
import github.paulmburu.local.models.FollowingEntity
import github.paulmburu.local.models.RepoEntity
import github.paulmburu.local.models.UserEntity

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        login = login,
        name = name,
        bio = bio,
        avatarUrl = avatarUrl,
        company = company,
        location = location,
        repos = repos,
        following = followers,
        followers = following
    )
}

fun RepoEntity.toDomain(): Repo {
    return Repo(
        id = id, name = name, description = description, stargazersCount = stargazersCount,
    )
}

fun FollowerEntity.toDomain(): Follower {
    return Follower(id = id, login = login, avatarUrl =  avatarUrl)
}

fun FollowingEntity.toDomain(): Following {
    return Following(id = id, login =  login, avatarUrl = avatarUrl)
}