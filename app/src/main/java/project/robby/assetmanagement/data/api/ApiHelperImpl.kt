package project.robby.assetmanagement.data.api

import project.robby.assetmanagement.data.response.login.LoginRequest
import project.robby.assetmanagement.data.response.login.LoginResponse
import retrofit2.Call
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
): ApiHelper {
    override fun signInWithEmailAndPassword(
        loginRequest: LoginRequest
    ): Call<LoginResponse> = apiService.signInWithEmailAndPassword(loginRequest)
}