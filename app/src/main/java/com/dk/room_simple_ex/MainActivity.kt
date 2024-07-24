package com.dk.room_simple_ex

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import com.dk.room_simple_ex.db.ImgDB
import com.dk.room_simple_ex.db.TextDB
import com.dk.room_simple_ex.db.entity.ImgEntity
import com.dk.room_simple_ex.db.entity.TextEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = TextDB.getDatabase(this)
        val db2 = ImgDB.getDatabase(this)
        val inputArea = findViewById<EditText>(R.id.textInputArea)

        val insertBtn = findViewById<Button>(R.id.insertBtn)
        val getAllBtn = findViewById<Button>(R.id.getDataBtn)
        val deleteBtn = findViewById<Button>(R.id.deleteBtn)

        val imgArea = findViewById<ImageView>(R.id.imgArea)
        val setImg = findViewById<Button>(R.id.setImgBtn)

        val imgArea2 = findViewById<ImageView>(R.id.imgArea2)
        val getImg = findViewById<Button>(R.id.getImgBtn)

        setImg.setOnClickListener {
            val drawable = imgArea.drawable
            val bitmap = drawable.toBitmap()
            CoroutineScope(Dispatchers.IO).launch {
                db2.imgDao().insert(ImgEntity(0, bitmap))
            }

        }

        getImg.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = db2.imgDao().getAllData()

                withContext(Dispatchers.Main){
                    imgArea2.setImageBitmap(result[0].myPhoto)
                }
            }
        }


        insertBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.textDao().insert(TextEntity(0, inputArea.text.toString(), Calendar.getInstance().time ))
                inputArea.setText("")
            }
        }

        getAllBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("MAIN", db.textDao().getAllData().toString())
            }
        }

        deleteBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.textDao().deleteAllData()
            }
        }

    }
}