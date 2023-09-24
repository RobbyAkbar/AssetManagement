package project.robby.assetmanagement.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import project.robby.assetmanagement.R
import project.robby.assetmanagement.core.designsystem.MyIcons
import project.robby.assetmanagement.data.response.login.LoginResponse
import project.robby.assetmanagement.ui.components.CustomFilledButton
import project.robby.assetmanagement.ui.components.CustomOutlinedTextField
import project.robby.assetmanagement.ui.components.LoadingDialog
import project.robby.assetmanagement.ui.theme.AssetManagementTheme
import project.robby.assetmanagement.utils.DataHandler
import project.robby.assetmanagement.viewmodel.AuthViewModel

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val loginUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.message.collect {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    SignInScreen(
        modifier = modifier,
        loginUiState = loginUiState,
        onEmailChange = viewModel::onEmailChanged,
        onPasswordChange = viewModel::onPasswordChanged,
        onButtonLoginClick = viewModel::authUser,
        setLogged = viewModel::setLogged
    )
}

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    loginUiState: SignInUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onButtonLoginClick: () -> Unit,
    setLogged: (LoginResponse) -> Unit
) {
    val context = LocalContext.current

    if (loginUiState.isLoading) {
        LoadingDialog()
    }

    if (loginUiState.loginResponse is DataHandler.Success<LoginResponse?>) {
        val data = (loginUiState.loginResponse as DataHandler.Success<LoginResponse?>).data
        data?.let { setLogged(it) }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.login_banner_top),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp)
            ) {
                Image(
                    modifier = Modifier.width(51.dp),
                    painter = painterResource(id = R.mipmap.ic_launcher),
                    contentDescription = context.getString(R.string.app_name),
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    text = context.getString(R.string.txt_welcome), style = TextStyle(
                        fontSize = 32.sp,
                        lineHeight = 36.sp,
                        fontFamily = FontFamily(Font(R.font.red_hat_display_regular)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                    )
                )
                Text(
                    text = context.getString(R.string.txt_please_sign_in), style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 26.sp,
                        fontFamily = FontFamily(Font(R.font.red_hat_display_regular)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFECF3FA),
                        textAlign = TextAlign.Center,
                    )
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween
            ) {
                CustomOutlinedTextField(
                    text = loginUiState.email, placeholder = context.getString(R.string.email),
                    leadingIcon = MyIcons.Email, keyboardType = KeyboardType.Email,
                    onTextFieldValueChange = onEmailChange, isError = loginUiState.isEmailError,
                    errorMessage = context.getString(R.string.email_error)
                )
                CustomOutlinedTextField(
                    text = loginUiState.password,
                    placeholder = context.getString(R.string.password),
                    leadingIcon = MyIcons.Lock, keyboardType = KeyboardType.Password,
                    onTextFieldValueChange = onPasswordChange,
                    isError = loginUiState.isPasswordError, isDone = true,
                    errorMessage = context.getString(R.string.password_error)
                )
                CustomFilledButton(
                    modifier = Modifier
                        .fillMaxWidth().height(64.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    onClick = {
                              if (loginUiState.loginResponse !is DataHandler.Loading) {
                                  onButtonLoginClick()
                              }
                    }, text = context.getString(R.string.login),
                    isEnabled = loginUiState.isLoginButtonEnabled
                )
            }
        }
    }
}


@Preview
@Composable
fun SignInScreenPreview(viewModel: AuthViewModel = hiltViewModel()) {
    AssetManagementTheme {
        val loginUiState by viewModel.uiState.collectAsStateWithLifecycle()
        SignInScreen(
            onEmailChange = {},
            onPasswordChange = {},
            onButtonLoginClick = {},
            loginUiState = loginUiState,
            setLogged = {}
        )
    }
}