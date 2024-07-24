package com.dk.room_simple_ex.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dk.room_simple_ex.db.entity.ImgEntity
import com.dk.room_simple_ex.db.entity.TextEntity

@Dao
interface ImgDao {

    @Query("SELECT * FROM img_table")
    fun getAllData(): List<ImgEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(img: ImgEntity)

}