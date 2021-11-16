package com.example.starter.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.drawToBitmap
import androidx.fragment.app.viewModels
import com.example.starter.R
import com.example.starter.base.BaseFragment
import com.example.starter.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            tlRoot.apply {
                addTab(tlRoot.newTab().setText("레벨0"))
                addTab(tlRoot.newTab().setText("레벨1"))
                addTab(tlRoot.newTab().setText("레벨1.5"))
                addTab(tlRoot.newTab().setText("레벨2"))
                addTab(tlRoot.newTab().setText("주 4일"))
                addTab(tlRoot.newTab().setText("3분할"))
                addTab(tlRoot.newTab().setText("BB스트렝스"))
                addTab(tlRoot.newTab().setText("여성용 비키니"))
                addTab(tlRoot.newTab().setText("1 Lite Version"))
                addTab(tlRoot.newTab().setText("군디빌딩"))

                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab?.let {
                            tvMain.text = "${it.text} 프로그램"
                        }
                    }
                    override fun onTabUnselected(tab: TabLayout.Tab?) {}
                    override fun onTabReselected(tab: TabLayout.Tab?) {}
                })
            }

        }
    }

}