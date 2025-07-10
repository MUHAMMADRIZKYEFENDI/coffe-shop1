package com.example.coffeshopp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class detail_item : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_item)

        val backButton = findViewById<ImageView>(R.id.imageView7)
        val addToCartBtn = findViewById<ConstraintLayout>(R.id.addToCartBtn)
        val titleTxt = findViewById<TextView>(R.id.titleTxt)
        val priceTxt = findViewById<TextView>(R.id.priceTxt)
        val numberItemTxt = findViewById<TextView>(R.id.numberItemTxt)
        val picMain = findViewById<ImageView>(R.id.picMain)

        // Tombol kembali
        backButton.setOnClickListener {
            finish()
        }

        // Tombol Add to Cart
        addToCartBtn.setOnClickListener {
            val title = titleTxt.text.toString()

            // Ambil harga dan bersihkan dari RP, titik, spasi, dll
            val priceString = priceTxt.text.toString()
                .replace("RP", "", ignoreCase = true)
                .replace("Rp", "", ignoreCase = true)
                .replace(".", "")
                .replace(",", "")
                .replace(" ", "")
                .trim()

            val price = priceString.toDoubleOrNull() ?: 0.0
            val quantity = numberItemTxt.text.toString().toIntOrNull() ?: 1

            // Gambar masih statis, gunakan dari drawable
            val imageResId = R.drawable.espersso

            // Kirim ke halaman Cart
            val intent = Intent(this, cart::class.java).apply {
                putExtra("title", title)
                putExtra("price", price.toString())  // Kirim sebagai String agar konsisten
                putExtra("quantity", quantity.toString())
                putExtra("imageResId", imageResId)
            }
            startActivity(intent)
        }
    }
}
