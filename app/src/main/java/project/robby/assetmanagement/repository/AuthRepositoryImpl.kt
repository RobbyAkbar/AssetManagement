package project.robby.assetmanagement.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import project.robby.assetmanagement.data.api.ApiHelper
import project.robby.assetmanagement.data.response.login.LoginRequest
import project.robby.assetmanagement.data.response.login.LoginResponse
import project.robby.assetmanagement.utils.DataHandler
import javax.inject.Inject

object DataStoreKey {
    val OnLoggedKey = booleanPreferencesKey("on_logged")
    val idKey = stringPreferencesKey("id")
    val emailKey = stringPreferencesKey("email")
    val usernameKey = stringPreferencesKey("username")
    val statusKey = booleanPreferencesKey("status")
    val tokenKey = stringPreferencesKey("token")
}

class AuthRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val dataStore: DataStore<Preferences>
) : AuthRepository {
    override fun login(loginRequest: LoginRequest): Flow<DataHandler<LoginResponse?>> = flow {
        val client = apiHelper.signInWithEmailAndPassword(loginRequest)
        try {
            val response = client.execute()
            if (response.isSuccessful) {
                val data = response.body()
                emit(DataHandler.Success(data))
            } else {
                emit(DataHandler.Error("User tidak ditemukan!"))
            }
        } catch (e: Exception) {
            emit(DataHandler.Error(e.message ?: "Gagal Login!"))
        }
    }.flowOn(Dispatchers.IO)

    override fun logout() {
        /* TODO */
    }

    override fun isAlreadyLogged(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[DataStoreKey.OnLoggedKey] ?: false
    }

    override fun setLogged(loginResponse: LoginResponse) {
        runBlocking {
            dataStore.edit { settings ->
                settings[DataStoreKey.OnLoggedKey] = true
                settings[DataStoreKey.idKey] = loginResponse.userId ?: ""
                settings[DataStoreKey.emailKey] = loginResponse.email ?: ""
                settings[DataStoreKey.usernameKey] = loginResponse.username ?: ""
                settings[DataStoreKey.statusKey] = loginResponse.isActive ?: false
                settings[DataStoreKey.tokenKey] = loginResponse.token ?: ""
            }
        }
    }
}

