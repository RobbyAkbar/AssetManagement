package project.robby.assetmanagement.data.api

import project.robby.assetmanagement.data.response.login.LoginRequest
import project.robby.assetmanagement.data.response.login.LoginResponse
import retrofit2.Call

interface ApiHelper {
    fun signInWithEmailAndPassword(
        loginRequest: LoginRequest
    ): Call<LoginResponse>
}