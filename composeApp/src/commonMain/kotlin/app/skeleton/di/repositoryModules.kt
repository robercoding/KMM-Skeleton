package app.skeleton.di

import app.skeleton.data.FooRepository

val repositoryModule = org.koin.dsl.module {
    single { FooRepository(get()) }
}