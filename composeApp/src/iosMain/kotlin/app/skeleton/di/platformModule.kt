package app.skeleton.di

import app.skeleton.IOSPlatform
import app.skeleton.Platform
import org.koin.dsl.module

actual val platformModule = module {
    single<Platform> { IOSPlatform() }
}