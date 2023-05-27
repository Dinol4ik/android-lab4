package com.example.lab4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.BD.ShortKatDB
import com.example.lab4.model.SomethingData

class EndActivity: AppCompatActivity() {

    companion object{
        const val SOMETHING_MODEL = "sgm"
    }
    val ShortKatDB = ShortKatDB(this);

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        ShortKatDB.openDB();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.end_layaot)
        title = "Подробнее"
        val model = intent.getSerializableExtra(SOMETHING_MODEL) as? SomethingData
        findViewById<TextView>(R.id.text_name_task).text = model?.title
        findViewById<TextView>(R.id.text_about_task).text = model?.description

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<Button>(R.id.buttonDelete).setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Задача выполнена, Вы молодец!")
            ShortKatDB.deleteInBD(model?.id.toString())
            builder.setPositiveButton("OK"){dialogInterface, which ->
                val intent = Intent(this, MainActivity::class.java )
                startActivity(intent)
            }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
