package com.ahmedtikiwa.felinelove.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedtikiwa.felinelove.databinding.LoadStateFooterBinding

class CatResultsLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<CatResultsLoadStateAdapter.CatResultsLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CatResultsLoadStateViewHolder {
        val withDataBinding = LoadStateFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CatResultsLoadStateViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CatResultsLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class CatResultsLoadStateViewHolder(private val binding: LoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonLoadStateRetry.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBarLoadState.isVisible = loadState is LoadState.Loading
                buttonLoadStateRetry.isVisible = loadState is LoadState.Error
                textViewLoadStateError.isVisible = loadState is LoadState.Error

                if (loadState is LoadState.Error) {
                    textViewLoadStateError.text = loadState.error.localizedMessage
                }
            }
        }
    }
}