package com.app.lorempiscum

import com.app.lorempiscum.di.createNetworkClient
import com.app.lorempiscum.network.RemoteLoremApi
import com.app.lorempiscum.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single { createNetworkClient(BuildConfig.BASE_URL_API) }
    single { (get() as Retrofit).create(RemoteLoremApi::class.java) }
    viewModel { MainViewModel(get()) }
}