package net.megastudy.datastorepractice

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class DataStoreUtil(val context: Context) {


    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


    companion object {
        val Id = stringPreferencesKey("id")
    }

    suspend fun SaveId(id: String) {
        context.dataStore.edit {
            it[Id] = id
        }
    }

    fun getId() = context.dataStore.data.map { preferences ->
        // No type safety.
        preferences[Id] ?: ""
    }
}