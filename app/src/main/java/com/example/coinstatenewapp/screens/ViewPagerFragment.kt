package com.example.coinstatenewapp.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.coinstatenewapp.adapter.ViewPagerAdapter
import com.example.coinstatenewapp.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator


class ViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)

        val fragmentList = listOf<Fragment>(
            HomeFragment.getNewInstance(),
            FavoriteFragment.getNewInstance(),
        )

        val adapter = ViewPagerAdapter(fragmentList, childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter


        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "ALL"
                }
                1 -> {
                    tab.text = "FAVORITE"
                }
            }
        }.attach()

//        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                getDataFromDB(position)
//            }
//
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
////                getDataFromDB(position)
//
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//                super.onPageScrollStateChanged(state)
//            }
//
//            fun getDataFromDB(position:Int) {
//                val fr = fragmentList[position]
//                if (fr is FavoriteFragment) {
//                    fr.getDataFromDB()
//                }
//            }
//        })

        return binding.root


    }
}