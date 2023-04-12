package com.example.coinstatenewapp.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinstatenewapp.databinding.ItemCoinBinding
import com.example.coinstatenewapp.model.Coin
import com.example.coinstatenewapp.utils.CoinViewHolder
import com.example.coinstatenewapp.utils.PrefsManager
import java.lang.ref.WeakReference

class FavoriteAdapter(val activity: Activity) : RecyclerView.Adapter<CoinViewHolder>() {
    private var coinsList = emptyList<Coin>()
    var delegate: WeakReference<CoinsAdapterDelegate>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return coinsList.size
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.binding.nameTextView.text = coinsList[position].name
        holder.binding.priceBtcTextView.text = "${coinsList[position].priceBtc} BTC"

        holder.binding.root.setOnClickListener {
            delegate?.get()?.onCoinItemClick(coinsList[position])
        }
        holder.binding.favoriteImageView.visibility = View.GONE
        coinsList[position].isFavorite = PrefsManager.getFavorite(activity, coinsList[position].id)

        Glide.with(holder.binding.root.context)
            .load(coinsList[position].icon)
            .into(holder.binding.logoImageView)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Coin>) {
        coinsList = list
        notifyDataSetChanged()
    }
}