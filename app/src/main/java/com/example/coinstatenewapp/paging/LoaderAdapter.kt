package com.example.coinstatenewapp.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coinstatenewapp.databinding.LoaderItemBinding

class LoaderAdapter : LoadStateAdapter<LoaderAdapter.LoaderViewHolder>() {

    class LoaderViewHolder(val binding: LoaderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.progressBar2.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val binding = LoaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoaderViewHolder(binding)
    }
}