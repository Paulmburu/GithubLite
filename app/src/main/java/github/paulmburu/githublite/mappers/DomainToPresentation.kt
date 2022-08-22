package github.paulmburu.githublite.mappers

import github.paulmburu.domain.models.Follower
import github.paulmburu.domain.models.Following
import github.paulmburu.domain.models.Repo
import github.paulmburu.domain.models.User
import github.paulmburu.githublite.models.FollowerPresentation
import github.paulmburu.githublite.models.FollowingPresentation
import github.paulmburu.githublite.models.RepoPresentation
import github.paulmburu.githublite.models.UserPresentation


fun User.toPresentation(): UserPresentation {
    return UserPresentation(
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

fun Repo.toPresentation(): RepoPresentation {
    return RepoPresentation(
        id = id,
        name = name,
        description = description,
        stargazersCount = stargazersCount.toString()
    )
}

fun Follower.toPresentation(): FollowerPresentation {
    return FollowerPresentation(
        id = id, login = login, avatarUrl = avatarUrl
    )
}

fun Following.toPresentation(): FollowingPresentation {
    return FollowingPresentation(
        id = id, login = login, avatarUrl = avatarUrl
    )
}