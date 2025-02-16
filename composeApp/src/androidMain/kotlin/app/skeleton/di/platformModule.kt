package app.skeleton.di

import app.skeleton.AndroidPlatform
import app.skeleton.Platform
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::AndroidPlatform) bind Platform::class
}