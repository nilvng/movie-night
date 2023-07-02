package com.nillin.movienight.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE id IN (:id)")
    fun getUser(id: String): Flow<User>

    @Update
    fun updateUser(user: User)

    @Insert
    fun cacheUser(user: User)
}