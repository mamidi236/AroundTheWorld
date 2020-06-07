package com.raghav.aroundtheworld.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raghav.aroundtheworld.R
import com.raghav.aroundtheworld.data.SingleCategoryItem
import com.raghav.aroundtheworld.databinding.ItemWatchBinding


class SingleCategoryAdapter(var list:List<SingleCategoryItem>,
                            var context: Context, var clickListener:recyclerItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val biniding = DataBindingUtil.inflate<ItemWatchBinding>(LayoutInflater.from(parent.context),R.layout.item_watch,parent,false)

        return HomeItemViewHolder(biniding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return position %2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeItemViewHolder).bind(list[position])
    }

    inner class HomeItemViewHolder(val watchBinding: ItemWatchBinding) : RecyclerView.ViewHolder(watchBinding.root), View.OnClickListener {
        fun bind(item: SingleCategoryItem){
            watchBinding.root.setOnClickListener(this)
            watchBinding.categoryItem = item
            watchBinding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            clickListener.onItemClick(adapterPosition)
        }
    }


    interface recyclerItemClickListener{
        fun onItemClick(position: Int)
    }
}

@BindingAdapter("android:src")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}
