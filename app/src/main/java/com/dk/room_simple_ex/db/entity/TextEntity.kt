package com.dk.room_simple_ex.db.entity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.util.Date

@Entity(tableName = "text_table")
data class TextEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "text")
    val text : String,

    @ColumnInfo(name = "currentDate")
    val currentDate : Date,
)

class TxtConverters{

    @TypeConverter
    fun fromTimestampToDate(value: Long) : Date {
        return Date(value)
    }

    @TypeConverter
    fun fromDateToTimestamp(date: Date) : Long {
        return date.time
    }
}
