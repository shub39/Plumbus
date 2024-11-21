package com.shub39.plumbus.di

import com.shub39.plumbus.core.data.HttpClientFactory
import com.shub39.plumbus.info.data.network.KtorRemoteCharacterDataSource
import com.shub39.plumbus.info.data.network.RemoteCharacterDataSource
import com.shub39.plumbus.info.data.repository.DefaultCharacterRepo
import com.shub39.plumbus.info.domain.character.CharacterRepo
import com.shub39.plumbus.info.presentation.character_list.CLViewModel
import com.shub39.plumbus.info.presentation.episode_list.ELViewModel
import com.shub39.plumbus.info.presentation.location_list.LLViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create( get() ) }
    singleOf(::KtorRemoteCharacterDataSource).bind<RemoteCharacterDataSource>()
    singleOf(::DefaultCharacterRepo).bind<CharacterRepo>()

    viewModelOf(::CLViewModel)
    viewModelOf(::ELViewModel)
    viewModelOf(::LLViewModel)
}