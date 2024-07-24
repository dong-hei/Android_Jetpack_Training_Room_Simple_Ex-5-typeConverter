package com.dk.room_simple_ex.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dk.room_simple_ex.db.dao.TextDao
import com.dk.room_simple_ex.db.entity.TextEntity
import com.dk.room_simple_ex.db.entity.TxtConverters

@Database(
    entities = [TextEntity::class],
    version = 2)
@TypeConverters(TxtConverters::class)
abstract class TextDB : RoomDatabase() {

    abstract fun textDao() : TextDao
    companion object{

        @Volatile
        private var INSTANCE : TextDB? = null

        fun getDatabase(context: Context) : TextDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TextDB::class.java,
                    "text_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        }
    }