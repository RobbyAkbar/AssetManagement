package project.robby.assetmanagement.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import project.robby.assetmanagement.data.response.login.LoginRequest
import project.robby.assetmanagement.data.response.login.LoginResponse
import project.robby.assetmanagement.repository.AuthRepository
import project.robby.assetmanagement.ui.screen.login.SignInUiState
import project.robby.assetmanagement.utils.DataHandler
import javax.inject.Inject

private data class AuthViewModelState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val email: String = "",
    val password: String = "",
    val loginResponse: DataHandler<LoginResponse?> = DataHandler.Init,
    val isLoginButtonEnabled: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false
) {

    fun toUiState(): SignInUiState {
        return SignInUiState.AuthUiState(
            isLoading = isLoading,
            isError = isError,
            loginResponse = loginResponse,
            isLoginButtonEnabled = !isEmailError && !isPasswordError
                    && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 8,
            email = email,
            password = password,
            isEmailError = if (email.isBlank()) false
                else !Patterns.EMAIL_ADDRESS.matcher(email).matches(),
            isPasswordError = if (password.isBlank()) false else password.length < 8
        )
    }
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _authViewModelState = MutableStateFlow(AuthViewModelState())

    private val _message: MutableSharedFlow<String> = MutableSharedFlow()
    val message: SharedFlow<String> = _message.asSharedFlow()

    val isAlreadyLogged: StateFlow<Boolean?> = repository.isAlreadyLogged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        )

    val uiState: StateFlow<SignInUiState> = _authViewModelState
        .map { it.toUiState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = _authViewModelState.value.toUiState()
        )

    fun setLogged(user: LoginResponse) {
        repository.setLogged(user)
    }

    fun onEmailChanged(newEmailValue: String) {
        _authViewModelState.update { it.copy(email = newEmailValue) }
    }

    fun onPasswordChanged(newPasswordValue: String) {
        _authViewModelState.update { it.copy(password = newPasswordValue) }
    }

    fun authUser() = viewModelScope.launch {
        _authViewModelState.update { it.copy(isLoading = true) }
        repository.login(LoginRequest(
            _authViewModelState.value.email, _authViewModelState.value.password
        )).collect { loginResponse ->
                if (loginResponse is DataHandler.Error) {
                    _message.emit(loginResponse.message)
                    _authViewModelState.update { it.copy(isError = true) }
                } else {
                    _authViewModelState.update { it.copy(loginResponse = loginResponse) }
                }
                _authViewModelState.update { it.copy(isLoading = false) }
            }
    }

}