package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.BD.ShortKatDB
import com.google.android.material.textfield.TextInputEditText

class AddActivity: AppCompatActivity() {
    val ShortKatDB = ShortKatDB(this);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Добавление"
        setContentView(R.layout.add_layaot)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ShortKatDB.openDB();

        findViewById<Button>(R.id.button).setOnClickListener {
            val value_title = findViewById<TextInputEditText>(R.id.text_name_task).text.toString()
            val value_desc = findViewById<TextInputEditText>(R.id.text_about_task).text.toString()
            if (value_title == ""){
                Toast.makeText(applicationContext,"Введите обязательные поля",Toast.LENGTH_LONG).show()
            }
            else{
                ShortKatDB.insertToDB(value_title, value_desc);
                val intent = Intent(this@AddActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ShortKatDB.closeDB();
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}