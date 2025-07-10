package com.example.coffeshopp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Klik gambar Espresso ke detail_item
        val imageEspresso = findViewById<ImageView>(R.id.imageEspresso)
        imageEspresso.setOnClickListener {
            val intent = Intent(this, detail_item::class.java)
            startActivity(intent)
        }

        // Klik teks Cappuccino ke list_item
        val textCappuccino = findViewById<TextView>(R.id.textCappuccino)
        textCappuccino.setOnClickListener {
            val intent = Intent(this, list_item::class.java)
            startActivity(intent)
        }

        // Klik tombol Cart (LinearLayout) ke cart
        val btnCart = findViewById<LinearLayout>(R.id.btnCart)
        btnCart.setOnClickListener {
            val intent = Intent(this, cart::class.java)
            startActivity(intent)
        }
    }
}
