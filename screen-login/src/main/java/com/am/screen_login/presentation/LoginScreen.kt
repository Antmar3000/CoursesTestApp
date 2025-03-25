package com.am.screen_login.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.am.core.presentation.NavRoutes
import com.am.screen_login.R

@Composable
fun LoginScreen(navController: NavController) {

    val context = LocalContext.current

    val emailInput = remember { mutableStateOf("") }
    val passwordInput = remember { mutableStateOf("") }

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                contentAlignment = Alignment.CenterStart
            ) {

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                    Text(
                        text = "Вход",
                        fontSize = 30.sp
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Email",
                        fontSize = 20.sp
                    )

                    BaseTextField("example@gmail.com", emailInput)

                    Text(
                        text = "Пароль",
                        fontSize = 20.sp
                    )

                    BaseTextField("Введите пароль", passwordInput)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 15.dp,
                        horizontal = 10.dp
                    ),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                        onClick = {
                            if (emailInput.value.isNotEmpty() && passwordInput.value.isNotEmpty()) {
                                onClickLogin(emailInput.value, context, navController)
                            } else {
                                Toast.makeText(context, "Заполните поля входа", Toast.LENGTH_SHORT).show()
                            }
                        }) {
                        Text("Вход")
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text("Нету аккаунта?")
                        Text("Регистрация",
                            color = Color.Green,
                            modifier = Modifier.clickable {
                                Toast.makeText(context, "Регистрация", Toast.LENGTH_SHORT).show()
                            })
                    }

                    Text("Забыл пароль",
                        color = Color.Green,
                        modifier = Modifier.clickable {
                            Toast.makeText(context, "Забыл пароль", Toast.LENGTH_SHORT).show()
                        })

                    Spacer(modifier = Modifier.height(10.dp))

                    HorizontalDivider(modifier = Modifier.height(2.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                        ) {
                            Image(
                                painterResource(R.drawable.vk),
                                "",
                                modifier = Modifier.size(30.dp)
                            )
                        }

                        Button(
                            onClick = {},
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                        ) {
                            Image(
                                painterResource(R.drawable.ok),
                                "",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

fun onClickLogin (inputEmail : String,
                  context: Context,
                  navController: NavController) {
    if (inputEmail.matches("^[a-zA-Z0-9+_.-]+@[a-z]+\\.+[a-z]+".toRegex())) {
        navController.navigate(NavRoutes.MAIN_LIST)
    } else {
        Toast.makeText(context, "Email введён неправильно", Toast.LENGTH_SHORT).show()
    }
}

//@Preview
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen()
//}