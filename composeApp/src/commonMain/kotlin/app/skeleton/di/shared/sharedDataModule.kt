package app.skeleton.di.shared

import app.skeleton.data.preferences.SettingsPreference
import org.koin.core.module.Module
import org.koin.dsl.module

val sharedDataModule: Module = module {
    single { SettingsPreference(get()) }
}