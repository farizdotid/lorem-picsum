package com.app.lorempiscum.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.lorempiscum.BR
import com.app.lorempiscum.R
import com.app.lorempiscum.databinding.ItemImageBinding
import com.app.lorempiscum.model.local.Image

class ImagesAdapter(
    var context: Context, var list: ArrayList<Image>
) :
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    // Inflating Layout and ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemImageBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_image, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    // Bind data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun addData(images: List<Image>) {
        list.addAll(images)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Image) {
            binding.setVariable(
                BR.image,
                data
            )
            binding.executePendingBindings()
        }
    }

}