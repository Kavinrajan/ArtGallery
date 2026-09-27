package com.kavin.artgallery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kavin.artgallery.R
import com.kavin.artgallery.databinding.TagItemLayoutBinding
import com.kavin.artgallery.model.TagModel

class TagsAdapter(val onTagSelected: (tag: TagModel, position: Int) -> Unit) : RecyclerView.Adapter<TagsAdapter.TagViewHolder>() {

    private val tagItems: ArrayList<TagModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding = TagItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(tagItems[position], position)
    }

    override fun getItemCount() = tagItems.size

    fun updateItems(tagsList: List<TagModel>) {
        tagItems.clear()
        tagItems.addAll(tagsList)
        notifyDataSetChanged()
    }

    inner class TagViewHolder(val itemBinding: TagItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(tagModel: TagModel, position: Int) {
            itemBinding.apply {
                txtTagName.text = tagModel.tagName
                imgTag.load(tagModel.imageUrl) {
                    placeholder(R.color.color_box_background)
                    crossfade(true)
                }

                cardTag.setOnClickListener {
                    onTagSelected(tagModel, position)
                }
            }
        }
    }
}
