package com.nillin.movienight.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val email: String,
    val bio: String,
    val avatar: String,
    val role: UserRole
        )

enum class UserRole(val value: Int) {
    NEWBIE(0),
    INSIDER(1),
    CRITIC(2),
}