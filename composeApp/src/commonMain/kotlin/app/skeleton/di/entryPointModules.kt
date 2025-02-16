package app.skeleton.di

import app.skeleton.di.shared.sharedDataModule

val entryPointModules = listOf(defaultModules, networkModule, sharedDataModule, dataModule, repositoryModule, viewModelModules, useCaseModules, platformModule)