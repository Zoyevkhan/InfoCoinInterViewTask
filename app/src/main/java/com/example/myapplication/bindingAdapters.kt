package com.example.myapplication

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


@BindingAdapter("setAdapterforListing", "listener")
fun RecyclerView.setAdapterForListing(
    liveData: MutableLiveData<UIState<List<Product>>>,
    listener: ListingListener
) {
    if (liveData.value is UIState.Success) {
        this.adapter = this.adapter ?: ListingAdapter(this.context, listener)
        ((this.adapter) as ListingAdapter).updateUI(liveData.value?.data ?: mutableListOf())
        this.layoutManager = this.layoutManager ?: GridLayoutManager(this.context, 2)
    }
}

@BindingAdapter("visibility")
fun ProgressBar.setProgressVisibility(
    liveData: MutableLiveData<UIState<List<Product>>>,
) {
    if (liveData.value is UIState.Success || liveData.value is UIState.Eror) {
        this.visibility = View.GONE
        if (liveData.value is UIState.Eror) {
            Toast.makeText(
                this.context,
                liveData.value?.message ?: "Something Went Wrong",
                Toast.LENGTH_LONG
            ).show()
        }
    } else {
        this.visibility = View.VISIBLE
    }
}


@BindingAdapter("url")
fun ImageView.setAdapterForListing(
    url:String?
) {
    url?.let {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }

}
