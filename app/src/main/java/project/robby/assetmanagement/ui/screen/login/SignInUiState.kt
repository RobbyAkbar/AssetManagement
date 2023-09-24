package project.robby.assetmanagement.ui.screen.login

import project.robby.assetmanagement.data.response.login.LoginResponse
import project.robby.assetmanagement.utils.DataHandler

sealed interface SignInUiState {
    val isLoading: Boolean
    val isError: Boolean
    val isLoginButtonEnabled: Boolean
    val email: String
    val password: String
    val loginResponse: DataHandler<LoginResponse?>
    val isEmailError: Boolean
    val isPasswordError: Boolean

    data class AuthUiState(
        override val isLoading: Boolean = false,
        override val isError: Boolean = false,
        override val loginResponse: DataHandler<LoginResponse?> = DataHandler.Init,
        override val isLoginButtonEnabled: Boolean = false,
        override val email: String,
        override val password: String,
        override val isEmailError: Boolean,
        override val isPasswordError: Boolean
    ) : SignInUiState
}