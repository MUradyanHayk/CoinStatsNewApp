package com.example.coinstatenewapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinstatenewapp.R
import com.example.coinstatenewapp.adapter.CoinsAdapter
import com.example.coinstatenewapp.adapter.CoinsAdapterDelegate
import com.example.coinstatenewapp.adapter.FavoriteAdapter
import com.example.coinstatenewapp.databinding.FragmentFavoriteBinding
import com.example.coinstatenewapp.model.Coin
import com.example.coinstatenewapp.viewModel.DetailViewModel
import com.example.coinstatenewapp.viewModel.FavoriteViewModel
import com.example.coinstatenewapp.viewModel.HomeViewModel
import java.lang.ref.WeakReference

class FavoriteFragment : Fragment(), CoinsAdapterDelegate {
    private lateinit var binding: FragmentFavoriteBinding
    private var adapter: FavoriteAdapter? = null
    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this)[FavoriteViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
    }


    private fun initialization() {
        viewModel.createDBIfNeeded()
        adapter = FavoriteAdapter(requireActivity())
        adapter?.delegate = WeakReference(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)



        viewModel.coins.observe(viewLifecycleOwner) { coins ->
            adapter?.setList(coins)
            if (coins.isEmpty()) {
                showHasNotFavoriteTextView()
            } else {
                hideHasNotFavoriteTextView()
            }
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) {
            adapter?.notifyDataSetChanged()
        }
    }

    private fun hideHasNotFavoriteTextView() {
        binding.hasNotFavoriteTextView.visibility = View.GONE
    }

    private fun showHasNotFavoriteTextView() {
        binding.hasNotFavoriteTextView.visibility = View.VISIBLE
    }

    override fun onCoinItemClick(coin: Coin) {
        val bundle = Bundle()
        bundle.putSerializable("currentCoin", coin)
        findNavController().navigate(R.id.action_viewPagerFragment_to_detailFragment, bundle)
    }

    override fun onCoinFavoriteItemClick(coin: Coin) {
        viewModel.onCoinFavoriteClick(coin)
    }

    fun getDataFromDB() {
        viewModel.getDataFromDB()
    }

    override fun onResume() {
        super.onResume()
        getDataFromDB()
    }

    companion object {
        fun getNewInstance() = FavoriteFragment()
    }
}