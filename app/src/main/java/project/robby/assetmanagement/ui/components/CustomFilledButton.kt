package project.robby.assetmanagement.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import project.robby.assetmanagement.R
import project.robby.assetmanagement.ui.theme.AssetManagementTheme
import project.robby.assetmanagement.ui.theme.Blue

@Composable
fun CustomFilledButton(modifier: Modifier = Modifier,
    onClick: () -> Unit, text: String, isEnabled: Boolean) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.LightGray
        ),
        enabled = isEnabled
    ) {
        Box(modifier = if (isEnabled) {
            Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF7DC1FF), Blue)
                    ), shape = RoundedCornerShape(5.dp)
                ).then(modifier)
        } else Modifier,
            contentAlignment = Alignment.Center) {
            Text(
                text = text,
                style = TextStyle(
                    color = if (isEnabled) Color.White else Color.DarkGray,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.red_hat_display_regular))
                )
            )
        }
    }
}

@Preview("default", showBackground = true)
@Composable
private fun CustomFilledButtonPreview() {
    AssetManagementTheme {
        CustomFilledButton(
            onClick = {},
            text = "Login",
            isEnabled = false
        )
    }
}