package com.dk.room_simple_ex.db.entity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

@Entity(tableName = "img_table")
data class ImgEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "photo")
    val myPhoto : Bitmap
)

class ImgConverters{
    @TypeConverter
    fun fromBitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun fromByteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}
