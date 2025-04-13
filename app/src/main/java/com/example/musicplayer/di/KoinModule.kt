package com.example.musicplayer.di

import com.example.data.network.NetworkService
import com.example.data.remote.MusicServiceImpl
import com.example.data.remote.RemoteService
import com.example.data.repository.MusicRepositoryImpl
import com.example.domain.repository.MusicRepository
import com.example.domain.usecase.MusicUseCase
import com.example.musicplayer.viewmodel.MusicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val musicModule = module {
    single { NetworkService.client }
    single<RemoteService> { MusicServiceImpl() }
    single<MusicRepository> { MusicRepositoryImpl(get()) }
    single { MusicUseCase(get()) }
    viewModel { MusicViewModel(get()) }
}