package app.skeleton.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class SettingsPreference(private val settingsPreference: DataStore<Preferences>) {

    companion object {
        const val PREFERENCE_THEME_IS_DARK_MODE = "is_dark_mode"
    }

    // Boolean region
    fun getBooleanFlow(preferenceKey: String): Flow<Boolean?> = settingsPreference.data.map { preferences ->
        preferences[booleanPreferencesKey(preferenceKey)]
    }

    fun getBooleanFlow(preferenceKey: String, ifNotAvailable: Boolean): Flow<Boolean> = settingsPreference.data.map { preferences ->
        preferences[booleanPreferencesKey(preferenceKey)] ?: ifNotAvailable
    }

    suspend fun getBooleanData(preferenceKey: String): Boolean? {
        val key = booleanPreferencesKey(preferenceKey)
        return settingsPreference.data.firstOrNull()?.get(key)
    }

    suspend fun setBooleanData(preferenceKey: String, value: Boolean) {
        val key = booleanPreferencesKey(preferenceKey)
        settingsPreference.edit { preferences ->
            preferences[key] = value
        }
    }


    // String region
    suspend fun getStringData(preferenceKey: String): String? {
        val key = stringPreferencesKey(preferenceKey)
        return settingsPreference.data.firstOrNull()?.get(key)
    }

    fun getStringFlow(preferenceKey: String): Flow<String?> = settingsPreference.data.map { preferences ->
        preferences[stringPreferencesKey(preferenceKey)]
    }

    suspend fun setStringData(preferenceKey: String, value: String?) {
        val key = stringPreferencesKey(preferenceKey)
        settingsPreference.edit { preferences ->
            if(value == null) {
                preferences.clear()
                return@edit
            }
            preferences[key] = value
        }
    }

    fun getStringDataFlow(preferenceKey: String): Flow<String?> = settingsPreference.data.map { preferences ->
        preferences[stringPreferencesKey(preferenceKey)]
    }

    fun getStringDataFlow(preferenceKey: String, ifNotAvailable: String): Flow<String?> = settingsPreference.data.map { preferences ->
        preferences[stringPreferencesKey(preferenceKey)] ?: ifNotAvailable
    }

    // Long region
    suspend fun getLongData(preferenceKey: String): Long? {
        val key = longPreferencesKey(preferenceKey)
        return settingsPreference.data.firstOrNull()?.get(key)
    }

    suspend fun setLongData(preferenceKey: String, value: Long) {
        val key = longPreferencesKey(preferenceKey)
        settingsPreference.edit { preferences ->
            preferences[key] = value
        }
    }

    fun getLongDataFlow(preferenceKey: String): Flow<Long?> = settingsPreference.data.map { preferences ->
        preferences[longPreferencesKey(preferenceKey)]
    }

    fun getLongDataFlow(preferenceKey: String, ifNotAvailable: Long): Flow<Long> = settingsPreference.data.map { preferences ->
        preferences[longPreferencesKey(preferenceKey)] ?: ifNotAvailable
    }
}