package project.robby.assetmanagement.repository

import kotlinx.coroutines.flow.Flow
import project.robby.assetmanagement.data.response.login.LoginRequest
import project.robby.assetmanagement.data.response.login.LoginResponse
import project.robby.assetmanagement.utils.DataHandler

interface AuthRepository {
    fun login(loginRequest: LoginRequest): Flow<DataHandler<LoginResponse?>>
    fun logout()

    fun isAlreadyLogged(): Flow<Boolean>
    fun setLogged(loginResponse: LoginResponse)
}
