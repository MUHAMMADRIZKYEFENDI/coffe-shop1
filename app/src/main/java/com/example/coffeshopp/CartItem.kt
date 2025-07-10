package com.example.coffeshopp

data class CartItem(
    val title: String,
    val imageResId: Int,
    val price: Double,
    var quantity: Int
)