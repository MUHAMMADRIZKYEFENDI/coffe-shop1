package com.example.coffeshopp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.*

class cart : AppCompatActivity(), CartAdapter.OnCartItemChangeListener {

    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartItems: MutableList<CartItem>

    private lateinit var recyclerView: RecyclerView
    private lateinit var totalFeeTxt: TextView
    private lateinit var deliveryTxt: TextView
    private lateinit var taxTxt: TextView
    private lateinit var totalTxt: TextView

    private val deliveryFee = 2000.0
    private val taxRate = 0.1 // 10%

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Inisialisasi UI
        recyclerView = findViewById(R.id.listView)
        totalFeeTxt = findViewById(R.id.totalFeeTxt)
        deliveryTxt = findViewById(R.id.deliveryTxt)
        taxTxt = findViewById(R.id.taxTxt)
        totalTxt = findViewById(R.id.totalTxt)

        // Ambil data dari intent (dari detail_item)
        val newItem = CartItem(
            title = intent.getStringExtra("title") ?: "Tidak ada judul",
            imageResId = intent.getIntExtra("imageResId", R.drawable.espersso),
            price = intent.getStringExtra("price")?.toDoubleOrNull() ?: 0.0,
            quantity = intent.getStringExtra("quantity")?.toIntOrNull() ?: 1
        )

        // Inisialisasi list cart
        cartItems = mutableListOf(newItem)

        // Setup adapter
        cartAdapter = CartAdapter(cartItems, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = cartAdapter

        // Hitung total awal
        calculateCartTotal()
    }

    override fun onCartUpdated() {
        calculateCartTotal()
    }

    private fun calculateCartTotal() {
        var subtotal = 0.0
        for (item in cartItems) {
            subtotal += item.price * item.quantity
        }

        val tax = subtotal * taxRate
        val total = subtotal + tax + deliveryFee

        totalFeeTxt.text = formatRupiah(subtotal)
        deliveryTxt.text = formatRupiah(deliveryFee)
        taxTxt.text = formatRupiah(tax)
        totalTxt.text = formatRupiah(total)
    }

    private fun formatRupiah(amount: Double): String {
        val localeID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localeID)
        return format.format(amount)
    }
}
