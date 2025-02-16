package app.skeleton.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.sqlite.driver.AndroidSQLiteDriver
import app.skeleton.data.preferences.PREFERENCE_DATA_STORE_SETTINGS
import app.skeleton.data.preferences.createSharedDataStore
import app.skeleton.database.FooDatabase
import app.skeleton.database.getDatabaseBuilder
import app.skeleton.database.getRoomDatabase
import app.skeleton.utils.applicationContext
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

private val dataStore: DataStore<Preferences> = createDataStore(applicationContext, PREFERENCE_DATA_STORE_SETTINGS)
actual val dataModule: Module = module {
    single { dataStore }
    single<FooDatabase> { getRoomDatabase(get(), AndroidSQLiteDriver()) }
    single { getDatabaseBuilder(androidContext()) }
}

fun createDataStore(context: Context, fileName: String): DataStore<Preferences> = createSharedDataStore { context.filesDir.resolve(fileName).absolutePath }