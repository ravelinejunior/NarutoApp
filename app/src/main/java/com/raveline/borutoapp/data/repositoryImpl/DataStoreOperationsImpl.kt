package com.raveline.borutoapp.data.repositoryImpl

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.raveline.borutoapp.domain.repository.DataStoreOperations
import com.raveline.borutoapp.utils.Constants.DATASTORE_PREFERENCE_KEY
import com.raveline.borutoapp.utils.Constants.DATASTORE_PREFERENCE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_PREFERENCE_NAME)

class DataStoreOperationsImpl(context: Context):DataStoreOperations {

    private object PreferenceKey{
        val onBoardingKey = booleanPreferencesKey(name = DATASTORE_PREFERENCE_KEY)
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingProcessState(completed: Boolean) {
        dataStore.edit { prefs ->
            prefs[PreferenceKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if(exception is IOException){
                    emit(emptyPreferences())
                }else{
                    val tAGI = "TAGPrefs"
                    Log.i(tAGI, "readOnBoardingStateError: $exception")
                    throw exception
                }
            }.map { prefs ->
                val onBoardingState = prefs[PreferenceKey.onBoardingKey]?:false
                val tAGI = "TAGPrefs"
                Log.i(tAGI, "readOnBoardingState: $onBoardingState")
                onBoardingState
            }
    }

}









