package app.skeleton.di

import app.skeleton.domain.GetAllFooUseCase
import org.koin.dsl.module

val useCaseModules = module {
    single { GetAllFooUseCase(get()) }
}