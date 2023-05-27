package com.example.lab4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.BD.ShortKatDB
import com.example.lab4.adapter.SimpleAdapter
import com.example.lab4.model.SomethingData

class MainActivity : AppCompatActivity() {

    private fun routeToSomethingActivity(model: SomethingData) {
        val intent = Intent(this, EndActivity::class.java).apply {
            putExtra(EndActivity.SOMETHING_MODEL, model)
        }
        startActivity(intent)
    }
    private val adapter by lazy {
        SimpleAdapter{
            routeToSomethingActivity(it)
        }
    }
    val ShortKatDB = ShortKatDB(this);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recycleView).apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
        ShortKatDB.openDB();
        val simpleItems = ShortKatDB.readDbData();
        adapter.set(simpleItems)

        findViewById<Button>(R.id.button2).setOnClickListener {
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        ShortKatDB.closeDB();
    }

}