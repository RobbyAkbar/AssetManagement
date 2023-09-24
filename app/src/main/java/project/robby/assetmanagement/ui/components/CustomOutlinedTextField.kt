package project.robby.assetmanagement.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import project.robby.assetmanagement.R
import project.robby.assetmanagement.core.designsystem.MyIcons
import project.robby.assetmanagement.ui.theme.AssetManagementTheme
import project.robby.assetmanagement.ui.theme.Blue300
import project.robby.assetmanagement.ui.theme.Blue500
import project.robby.assetmanagement.ui.theme.Red400

@Composable
fun CustomOutlinedTextField(
    text: String, placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextFieldValueChange: (String) -> Unit,
    isDone: Boolean = false,
    leadingIcon: ImageVector? = null,
    isError: Boolean = false,
    errorMessage: String = ""
) {
    val context = LocalContext.current
    var showPassword by remember { mutableStateOf(false) }
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = if (isDone) {
                ImeAction.Done
            } else {
                ImeAction.Next
            }
        ),
        visualTransformation = if (keyboardType == KeyboardType.Password) {
            if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        } else {
            VisualTransformation.None
        },
        leadingIcon = if (leadingIcon != null) {
            {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = placeholder,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        } else null,
        trailingIcon = if (keyboardType == KeyboardType.Password) {
            {
                if (showPassword) {
                    IconButton(onClick = { showPassword = false }) {
                        Icon(
                            MyIcons.Visibility,
                            contentDescription = context.getString(R.string.desc_password_show),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                } else {
                    IconButton(onClick = { showPassword = true }) {
                        Icon(
                            MyIcons.VisibilityOff,
                            contentDescription = context.getString(R.string.desc_password_hide),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        } else if (isError) {
            {
                Icon(MyIcons.Error,"error", tint = Red400)
            }
        } else null,
        value = text,
        onValueChange = onTextFieldValueChange,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.red_hat_display_regular))
        ),
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.red_hat_display_regular))
                )
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 10.5.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Blue500,
            focusedContainerColor = Blue300,
            errorBorderColor = Red400
        ),
        shape = RoundedCornerShape(5.dp),
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}

@Preview("password", showBackground = true)
@Composable
private fun CustomOutlinedTextFieldFilledPreview() {
    AssetManagementTheme {
        CustomOutlinedTextField(
            text = "robby123",
            placeholder = "Masukkan Password",
            keyboardType = KeyboardType.Password,
            onTextFieldValueChange = {}
        )
    }
}

@Preview("email", showBackground = true)
@Composable
private fun CustomOutlinedTextFieldEmptyPreview() {
    AssetManagementTheme {
        CustomOutlinedTextField(
            text = "",
            placeholder = "Masukkan Alamat Email",
            keyboardType = KeyboardType.Email,
            leadingIcon = MyIcons.Email,
            onTextFieldValueChange = {}
        )
    }
}

@Preview("error", showBackground = true)
@Composable
private fun CustomOutlinedTextFieldError() {
    AssetManagementTheme {
        CustomOutlinedTextField(
            text = "robby@gmail",
            placeholder = "Masukkan Alamat Email",
            keyboardType = KeyboardType.Email,
            leadingIcon = MyIcons.Email,
            onTextFieldValueChange = {},
            isError = true,
            errorMessage = "Invalid Email"
        )
    }
}