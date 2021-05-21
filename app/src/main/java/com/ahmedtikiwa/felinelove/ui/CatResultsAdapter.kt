package com.ahmedtikiwa.felinelove.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmedtikiwa.felinelove.R
import com.ahmedtikiwa.felinelove.databinding.CatResultItemBinding
import com.ahmedtikiwa.felinelove.domain.ImageSearchItem

class CatResultsAdapter :
    PagingDataAdapter<ImageSearchItem, CatResultsAdapter.CatResultsViewHolder>(CAT_COMPARATOR) {

    override fun onBindViewHolder(holder: CatResultsViewHolder, position: Int) {
        holder.binding.also {
            it.item = getItem(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatResultsViewHolder {
        val withDataBinding: CatResultItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CatResultsViewHolder.LAYOUT,
            parent,
            false
        )
        return CatResultsViewHolder(withDataBinding)
    }

    class CatResultsViewHolder(val binding: CatResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.cat_result_item
        }
    }

    companion object {
        private val CAT_COMPARATOR = object : DiffUtil.ItemCallback<ImageSearchItem>() {

            override fun areItemsTheSame(oldItem: ImageSearchItem, newItem: ImageSearchItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ImageSearchItem, newItem: ImageSearchItem) =
                oldItem == newItem
        }
    }
}