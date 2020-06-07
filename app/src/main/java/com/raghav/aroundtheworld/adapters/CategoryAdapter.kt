package com.raghav.aroundtheworld.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.raghav.aroundtheworld.R
import com.raghav.aroundtheworld.data.CategoryItem
import com.raghav.aroundtheworld.data.HomeItem

class CategoryAdapter(var list:List<CategoryItem>,
                      var context: Context, var clickListener:recyclerItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            0 ->HomeItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false))
            else -> HomeItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category_2,parent,false))
        }
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

    inner class HomeItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var backgroundImage: ImageView = itemView.findViewById(R.id.categoryImage) as ImageView
        var titleTv: TextView = itemView.findViewById(R.id.categoryTitle) as TextView
        var itemView = itemView.setOnClickListener(this)
        fun bind(item: CategoryItem){
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