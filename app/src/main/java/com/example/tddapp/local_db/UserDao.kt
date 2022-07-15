package com.example.tddapp.local_db

import androidx.room.*
import com.example.tddapp.UserBody

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userBody: UserBody)

    @Delete
    suspend fun delete(userBody: UserBody)

    @Update
    suspend fun update(userBody: UserBody)

    @Query("select * from UserBody")
    suspend fun getAll():List<UserBody>
}