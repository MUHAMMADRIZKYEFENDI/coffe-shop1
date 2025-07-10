package com.example.coffeshopp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import java.text.NumberFormat
import java.util.*

class CartAdapter(
    private val cartItems: MutableList<CartItem>,
    private val listener: OnCartItemChangeListener
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    interface OnCartItemChangeListener {
        fun onCartUpdated()
    }

    inner class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTxt: TextView = view.findViewById(R.id.titleTxt)
        val picCart: ShapeableImageView = view.findViewById(R.id.picCart)
        val feeEachItem: TextView = view.findViewById(R.id.feeEachItem)
        val numberItemTxt: TextView = view.findViewById(R.id.numberItemTxt)
        val totalEachItem: TextView = view.findViewById(R.id.totalEachItem)
        val plusBtn: TextView = view.findViewById(R.id.plusEachItem)
        val minusBtn: TextView = view.findViewById(R.id.minusEachItem)
        val removeBtn: ImageView = view.findViewById(R.id.removeItemBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]

        holder.titleTxt.text = item.title
        holder.picCart.setImageResource(item.imageResId)
        holder.feeEachItem.text = formatRupiah(item.price)
        holder.numberItemTxt.text = item.quantity.toString()
        holder.totalEachItem.text = formatRupiah(item.price * item.quantity)

        // Tombol tambah
        holder.plusBtn.setOnClickListener {
            item.quantity++
            notifyItemChanged(position)
            listener.onCartUpdated()
        }

        // Tombol kurang
        holder.minusBtn.setOnClickListener {
            if (item.quantity > 1) {
                item.quantity--
                notifyItemChanged(position)
                listener.onCartUpdated()
            }
        }

        // Tombol hapus
        holder.removeBtn.setOnClickListener {
            cartItems.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItems.size)
            listener.onCartUpdated()
        }
    }

    override fun getItemCount(): Int = cartItems.size

    fun getCartItems(): List<CartItem> = cartItems

    // Format harga jadi Rp
    private fun formatRupiah(number: Double): String {
        val localeID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localeID)
        return format.format(number)
    }
}
