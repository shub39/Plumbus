package com.shub39.plumbus.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.shub39.plumbus.core.data.HttpClientFactory
import com.shub39.plumbus.info.data.network.KtorRemoteDataSource
import com.shub39.plumbus.info.data.network.RemoteDataSource
import com.shub39.plumbus.info.data.repository.DefaultCharacterRepo
import com.shub39.plumbus.info.data.repository.DefaultEpisodeRepo
import com.shub39.plumbus.info.data.repository.DefaultLocationRepo
import com.shub39.plumbus.info.domain.CharacterRepo
import com.shub39.plumbus.info.presentation.character.CLViewModel
import com.shub39.plumbus.info.presentation.episode.ELViewModel
import com.shub39.plumbus.info.presentation.location.LLViewModel
import com.shub39.plumbus.info.data.database.DatabaseFactory
import com.shub39.plumbus.info.data.database.PlumbusDb
import com.shub39.plumbus.info.domain.EpisodeRepo
import com.shub39.plumbus.info.domain.LocationRepo
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    // database and httpclient
    singleOf(::KtorRemoteDataSource).bind<RemoteDataSource>()
    single { HttpClientFactory.create( get() ) }
    single {
        get<DatabaseFactory>()
            .create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<PlumbusDb>().characterDao }
    single { get<PlumbusDb>().episodeDao }
    single { get<PlumbusDb>().locationDao }

    // viewmodels
    viewModelOf(::CLViewModel)
    viewModelOf(::ELViewModel)
    viewModelOf(::LLViewModel)

    // repositories
    singleOf(::DefaultCharacterRepo).bind<CharacterRepo>()
    singleOf(::DefaultEpisodeRepo).bind<EpisodeRepo>()
    singleOf(::DefaultLocationRepo).bind<LocationRepo>()
}