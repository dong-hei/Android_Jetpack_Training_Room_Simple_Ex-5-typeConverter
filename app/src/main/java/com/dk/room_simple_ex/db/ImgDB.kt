package com.dk.room_simple_ex.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dk.room_simple_ex.db.dao.ImgDao
import com.dk.room_simple_ex.db.entity.ImgConverters
import com.dk.room_simple_ex.db.entity.ImgEntity

@Database(
    entities = [ImgEntity::class],
    version = 2)
@TypeConverters(ImgConverters::class)
abstract class ImgDB : RoomDatabase() {

    abstract fun imgDao() : ImgDao
    companion object{

        @Volatile
        private var INSTANCE : ImgDB? = null

        fun getDatabase(context: Context) : ImgDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ImgDB::class.java,
                    "img_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        }
    }