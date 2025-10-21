package io.github.sadeghit.loginregister.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.sadeghit.loginregister.data.local.dataStore.dataStore
import io.github.sadeghit.loginregister.data.model.User
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class LoginRepository @Inject constructor(
    @ApplicationContext context: Context
) {
    private val dataStore = context.dataStore

    companion object {
        val USERNAME = stringPreferencesKey("username")
        val PASSWORD = stringPreferencesKey("password")
        val LOGIN = booleanPreferencesKey("login")
    }

    suspend fun setUserData(user: User) {
        dataStore.edit {
            it[USERNAME] = user.username
            it[PASSWORD] = user.password
            it[LOGIN] = false
        }
    }

    suspend fun userDataValidation(user: User): Boolean {
        val prefs = dataStore.data.first()
        val valid = prefs[USERNAME] == user.username && prefs[PASSWORD] == user.password
        if (valid) {
            dataStore.edit { it[LOGIN] = true } // ✅ کاربر وارد شده
        }
        return valid
    }

    suspend fun setLoginState(state: Boolean) {
        dataStore.edit { it[LOGIN] = state }
    }

    suspend fun isLoggedIn(): Boolean {
        val prefs = dataStore.data.first()
        return prefs[LOGIN] ?: false
    }
}