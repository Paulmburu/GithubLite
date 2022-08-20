package github.paulmburu.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "login")
    val login: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "bio")
    val bio: String,

    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String,

    @ColumnInfo(name = "company")
    val company: String?,

    @ColumnInfo(name = "location")
    val location: String,

    @ColumnInfo(name = "repos")
    val repos: Int,

    @ColumnInfo(name = "followers")
    val followers: Int,

    @ColumnInfo(name = "following")
    val following: Int
)