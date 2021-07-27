package com.example.harajtask.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.harajtask.databinding.PagingLoadStateBinding

// Responsible for the loading and error item at Paging

class LoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = LoadStateViewHolder(PagingLoadStateBinding.inflate(LayoutInflater.from(parent.context),parent,false), retry)

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}

class LoadStateViewHolder(val binding: PagingLoadStateBinding, val retry: () -> Unit) : RecyclerView.ViewHolder(binding.root)
 {
     fun bind(loadState: LoadState) {
         binding.errorTitle.isVisible = loadState is LoadState.Error
         binding.errorDesc.isVisible = loadState is LoadState.Error
         binding.errorRetryButton.isVisible = loadState is LoadState.Error
         binding.errorRetryButton.setOnClickListener {
             retry()
         }
         binding.progress.isVisible = loadState is LoadState.Loading
     }
}