package github.paulmburu.network.mappers

import com.google.common.truth.Truth
import github.paulmburu.domain.models.Follower
import github.paulmburu.domain.models.Following
import github.paulmburu.domain.models.Repo
import github.paulmburu.domain.models.User
import github.paulmburu.network.models.FollowerDto
import github.paulmburu.network.models.FollowingDto
import github.paulmburu.network.models.RepoDto
import github.paulmburu.network.models.UserDto
import org.junit.Test

class NetworkToDomainTest {
    @Test
    fun `When UserDto toDomain is called, the Domain representation should be returned`() {
        val userDto = UserDto(
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

        Truth.assertThat(user).isEqualTo(userDto.toDomain())
    }

    @Test
    fun `When RepoDto toDomain is called, the Domain representation should be returned`() {
        val repoDto = RepoDto(
            id = 227655606,
            name = "algs4",
            description = "Algorithms, 4th edition textbook code and libraries",
            stargazersCount = 4
        )
        val repo = Repo(
            id = 227655606,
            name = "algs4",
            description = "Algorithms, 4th edition textbook code and libraries",
            stargazersCount = 4
        )

        val customRepo = repoDto.toDomain()
        Truth.assertThat(repo).isNotEqualTo(customRepo)
    }

    @Test
    fun `When FollowerDto toDomain is called, the Domain representation should be returned`() {
        val followerDto = FollowerDto(
            id = 24954467,
            login = "JohnDoe",
            avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
        )

        val follower = Follower(
            id = 24954467,
            login = "JohnDoe",
            avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
        )
        Truth.assertThat(follower).isEqualTo(followerDto.toDomain())
    }

    @Test
    fun `When FollowingDto toDomain is called, the Domain representation should be returned`() {
        val followingDto = FollowingDto(
            id = 24954467,
            login = "JohnDoe",
            avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
        )

        val following = Following(
            id = 24954467,
            login = "JohnDoe",
            avatarUrl = "https://avatars.githubusercontent.com/u/24954467?v=4",
        )
        Truth.assertThat(following).isEqualTo(followingDto.toDomain())
    }
}