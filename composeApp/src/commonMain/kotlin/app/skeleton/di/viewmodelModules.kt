package app.skeleton.di

import app.skeleton.ui.screens.home.HomeViewModel
import app.skeleton.ui.screens.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf

val viewModelModules = org.koin.dsl.module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::SettingsViewModel)
}