package project.robby.assetmanagement.data.api

import project.robby.assetmanagement.data.response.login.LoginRequest
import project.robby.assetmanagement.data.response.login.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("/auth/login")
    fun signInWithEmailAndPassword(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>
}