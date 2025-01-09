package io.kdbrian.urbandict.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.kdbrian.urbandict.R
import io.kdbrian.urbandict.ui.composables.BorderedRowWithIcon
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme
import io.kdbrian.urbandict.ui.theme.gambarino
import io.kdbrian.urbandict.ui.theme.telma
import io.kdbrian.urbandict.util.Shapes

@Composable
fun AuthScreen(modifier: Modifier = Modifier) {

    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    val passwordErrors by remember { mutableStateOf(listOf<String>()) }
    val icon by remember {
        derivedStateOf {
            if (showPassword) R.drawable.visibility else R.drawable.visibility_off
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            BorderedRowWithIcon(
                icon = R.drawable.google_logo,
                title = "Sign In With Google"
            )

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f))
                Text(
                    text = "Or",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontFamily = telma
                )
                HorizontalDivider(modifier = Modifier.weight(1f))
            }

            Spacer(Modifier.height(8.dp))

            BadgedBox(
                badge = {
                    Text(text = "*", modifier = Modifier.padding(horizontal = 4.dp))
                },
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                Text(text = "Email", fontWeight = FontWeight.SemiBold)
            }

            OutlinedTextField(
                value = email,
                onValueChange = setEmail,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                label = { Text(text = "email") }
            )

            Spacer(Modifier.height(12.dp))

            BadgedBox(
                badge = {
                    Text(text = "*", modifier = Modifier.padding(horizontal = 4.dp))
                },
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                Text(text = "Password", fontWeight = FontWeight.SemiBold)
            }

            OutlinedTextField(
                value = password,
                onValueChange = setPassword,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                label = { Text(text = "password") },
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            painter = painterResource(icon),
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
            )


            Spacer(Modifier.height(12.dp))

            Button(
                onClick = {},
                shape = Shapes.rounded12Dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {

                Text(text = "Sign In / Sign Up", fontFamily = gambarino)

            }

        }

    }


}

@Preview(showBackground = true)
@Composable
fun AuthScreenPrev(modifier: Modifier = Modifier) {
    UrbanDictTheme {
        AuthScreen()
    }
}