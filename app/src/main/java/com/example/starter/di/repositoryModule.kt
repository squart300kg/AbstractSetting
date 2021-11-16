package com.example.starter.di

import com.example.starter.repository.YoutubeRepository
import com.example.starter.repository.YoutubeRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    factory <YoutubeRepository> { YoutubeRepositoryImp(get()) }
}