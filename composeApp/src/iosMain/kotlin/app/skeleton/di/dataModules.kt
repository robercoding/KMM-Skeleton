package app.skeleton.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import app.skeleton.data.preferences.PREFERENCE_DATA_STORE_SETTINGS
import app.skeleton.data.preferences.createSharedDataStore
import app.skeleton.database.FooDatabase
import app.skeleton.database.getDatabaseBuilder
import app.skeleton.database.getRoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual val dataModule: Module = module {
    single { createDataStore() }
    single<FooDatabase> { getRoomDatabase(get(), BundledSQLiteDriver()) }
    single { getDatabaseBuilder() }
}

@OptIn(ExperimentalForeignApi::class)
private fun createDataStore(): DataStore<Preferences> = createSharedDataStore {
    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    requireNotNull(documentDirectory).path + "/$PREFERENCE_DATA_STORE_SETTINGS"
}