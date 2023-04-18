package com.example.coinstatenewapp.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinstatenewapp.R
import com.example.coinstatenewapp.databinding.ItemCoinBinding
import com.example.coinstatenewapp.model.Coin
import com.example.coinstatenewapp.utils.PrefsManager
import java.lang.ref.WeakReference

interface CoinsAdapterDelegate {
    fun onCoinItemClick(coin: Coin)
    fun onCoinFavoriteItemClick(coin: Coin)
}

class CoinsAdapter(val activity: Activity, val delegate: WeakReference<CoinsAdapterDelegate>? = null) : PagingDataAdapter<Coin, CoinsAdapter.CoinViewHolder>(COMPARATOR) {
    class CoinViewHolder(val activity: Activity, val binding: ItemCoinBinding, val delegate: CoinsAdapterDelegate) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: Coin) {
            binding.nameTextView.text = coin.name
            binding.priceBtcTextView.text = "${coin.priceBtc} BTC"

            binding.root.setOnClickListener {
                delegate.onCoinItemClick(coin)
            }
            binding.favoriteImageView.setOnClickListener {
                delegate.onCoinFavoriteItemClick(coin)
            }

            coin.isFavorite = PrefsManager.getFavorite(activity, coin.id)
            if (coin.isFavorite) {
                binding.favoriteImageView.setImageResource(R.drawable.ic_favorite)
            } else {
                binding.favoriteImageView.setImageResource(R.drawable.ic_not_favorite)
            }

            Glide.with(binding.root.context)
                .load(coin.icon)
                .into(binding.logoImageView)
        }
    }
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Coin>() {
            override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
                return oldItem == newItem
            }
        }

    }

    val differ = AsyncListDiffer(this, COMPARATOR)

    //    private var coinsList = emptyList<Coin>()
//    var delegate: WeakReference<CoinsAdapterDelegate>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(activity, binding, delegate?.get()!!)
    }

//    override fun getItemCount(): Int {
//        return differ.currentList.size
//    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Coin>) {
//        coinsList = list
        notifyDataSetChanged()
    }
}