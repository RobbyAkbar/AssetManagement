package project.robby.assetmanagement.data.response.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("id")
    val userId: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("is_active")
    val isActive: Boolean? = false,

    @field:SerializedName("token")
    val token: String? = null
)