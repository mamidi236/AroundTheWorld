package com.raghav.aroundtheworld.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raghav.aroundtheworld.R
import com.raghav.aroundtheworld.data.HomeItem
import com.raghav.aroundtheworld.databinding.ItemHomeBinding


class HomeAdapter(var list:List<HomeItem>,
                  var context:Context, var clickListener:recyclerItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemHomeBinding>(LayoutInflater.from(parent.context),R.layout.item_home,parent,false)
        return  HomeItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeItemViewHolder).bind(list[position])
    }

    inner class HomeItemViewHolder(val homeItemBinding:ItemHomeBinding) : RecyclerView.ViewHolder(homeItemBinding.root),View.OnClickListener {
        var backgroundImage:ImageView = homeItemBinding.backgroundIv
        var titleTv:TextView = homeItemBinding.titleTV
        fun bind(item:HomeItem){
            homeItemBinding.root.setOnClickListener(this)
            backgroundImage.setImageDrawable(context.getDrawable(item.imageSrc))
            titleTv.setText(item.text)
        }

        override fun onClick(v: View?) {
            clickListener.onItemClick(adapterPosition)
        }
    }
    interface recyclerItemClickListener{
        fun onItemClick(position: Int)
    }
}