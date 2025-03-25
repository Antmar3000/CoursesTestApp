package com.am.screen_login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BaseTextField(text : String, input : MutableState<String>) {

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = input.value,
            onValueChange = { input.value = it },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
                .background(color = Color.Black,
                    shape = CircleShape),
            textStyle = LocalTextStyle.current.copy(
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 16.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    if (input.value.isEmpty()) {
                        Text(
                            "Введите текст",
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}
