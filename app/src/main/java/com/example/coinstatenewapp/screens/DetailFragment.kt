package com.example.coinstatenewapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.coinstatenewapp.R
import com.example.coinstatenewapp.databinding.FragmentDetailBinding
import com.example.coinstatenewapp.model.Coin
import com.example.coinstatenewapp.viewModel.DetailViewModel
import com.example.coinstatenewapp.viewModel.HomeViewModel

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private var currentCoin: Coin? = null
    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization(view)
    }

    fun initialization(view: View) {
        viewModel.createDBIfNeeded()
        currentCoin = arguments?.getSerializable("currentCoin") as? Coin?
        val currentCoin = this.currentCoin ?: return
        binding.nameTextView.text = currentCoin.name
        setFavoriteImage(currentCoin.isFavorite)
        Glide.with(view.context)
            .load(currentCoin.icon)
            .into(binding.logoImageView)


        binding.favoriteImageView.setOnClickListener {
            viewModel.onCoinFavoriteClick(currentCoin)
        }
        viewModel.isFavorite.observe(viewLifecycleOwner) {
            setFavoriteImage(it)
        }
    }

    private fun setFavoriteImage(isFavorite: Boolean) {
        if (isFavorite) {
            binding.favoriteImageView.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.favoriteImageView.setImageResource(R.drawable.ic_not_favorite)
        }
    }
}