package com.example.movieapplication.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.movieapplication.database.entity.BaseEntity

interface BaseDao<T : BaseEntity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t: T)

    @Insert
    suspend fun insert(t: List<T>)

    @Update
    suspend fun update(t: T)

    @Delete
    suspend fun delete(t: T)
}