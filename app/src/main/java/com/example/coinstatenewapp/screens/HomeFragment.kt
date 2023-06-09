package com.example.coinstatenewapp.screens

import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinstatenewapp.R
import com.example.coinstatenewapp.adapter.CoinsAdapter
import com.example.coinstatenewapp.adapter.CoinsAdapterDelegate
import com.example.coinstatenewapp.databinding.FragmentHomeBinding
import com.example.coinstatenewapp.model.Coin
import com.example.coinstatenewapp.paging.LoaderAdapter
import com.example.coinstatenewapp.utils.NetManager
import com.example.coinstatenewapp.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import java.lang.ref.WeakReference

class HomeFragment : Fragment(), CoinsAdapterDelegate {
    private lateinit var binding: FragmentHomeBinding
    private var adapter: CoinsAdapter? = null
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
    }

    private fun initialization() {
        viewModel.createDBIfNeeded()
        adapter = CoinsAdapter(requireActivity(), WeakReference(this))
        binding.recyclerView.adapter = adapter?.withLoadStateHeaderAndFooter(
            footer = LoaderAdapter(),
            header = LoaderAdapter()
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        adapter?.addLoadStateListener { combinedLoadStates ->
            binding.progressBar.isVisible = combinedLoadStates.refresh == LoadState.Loading

            val refreshState = combinedLoadStates.refresh
            if (refreshState is LoadState.Error) {
                Snackbar.make(binding.root, refreshState.error.localizedMessage ?: "", Snackbar.LENGTH_SHORT).show()
            }
        }
//        viewModel.getCoinsFromServer()

//        viewModel.coins.observe(viewLifecycleOwner) { list ->
//            list.body()?.let { adapter?.setList(it.coins) }
//            hideProgressBar()
//        }

        viewModel.isFavorite.observe(viewLifecycleOwner) {
            adapter?.notifyDataSetChanged()
        }
        if (!NetManager.hasInternetConnection(requireContext())) {
//            Snackbar.make(binding.root, "Not Internet Connection", Snackbar.LENGTH_SHORT).show()
            Toast.makeText(requireContext(), "Not Internet Connection", Toast.LENGTH_SHORT).show()
            binding.progressBar.isVisible = false
            return
        }
        viewModel.getList().observe(viewLifecycleOwner) { list ->
            if (list != null) {
                adapter?.submitData(lifecycle, list)
            }
        }


    }


    override fun onCoinItemClick(coin: Coin) {
        val bundle = Bundle()
        bundle.putSerializable("currentCoin", coin)
        findNavController().navigate(R.id.action_viewPagerFragment_to_detailFragment, bundle)
    }

    override fun onCoinFavoriteItemClick(coin: Coin) {
        viewModel.onCoinFavoriteClick(coin)
    }

    companion object {
        const val TAG = "HomeFragment"
        fun getNewInstance() = HomeFragment()
    }
}