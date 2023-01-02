package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ProductListingItemBinding

class ListingAdapter(val context: Context, val callBack:ListingListener ): RecyclerView.Adapter<ListingAdapter.ProductViewHolder>() {
    var list:MutableList<Product> = mutableListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val binding =
            ProductListingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.product=list.get(position)
        holder.binding.callback=callBack
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun updateUI(list:List<Product>){
        this.list=list.toMutableList()
        notifyDataSetChanged()
    }
    class ProductViewHolder(var binding: ProductListingItemBinding): RecyclerView.ViewHolder(binding.root)
}