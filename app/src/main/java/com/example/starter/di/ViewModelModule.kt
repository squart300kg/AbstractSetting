package com.example.starter.di

import com.example.starter.ui.home.HomeViewModel
import com.example.starter.ui.message.MessageViewModel
import com.example.starter.ui.mypage.MyPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { MessageViewModel() }
    viewModel { MyPageViewModel() }
}