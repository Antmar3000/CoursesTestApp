package com.am.screen_login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.am.core.domain.isCyrillic

@Composable
fun BaseTextField(text: String, input: MutableState<String>) {

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = input.value,
            onValueChange = { input.value = it },
            visualTransformation = CyrillicHighlightTransformation(MaterialTheme.colorScheme.secondary),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
                .background(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = CircleShape
                ),
            textStyle = LocalTextStyle.current.copy(
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    if (input.value.isEmpty()) {
                        Text(
                            text,
                            color = Color.White.copy(alpha = 0.7f),
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

class CyrillicHighlightTransformation (private val textColor: Color) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val builder = AnnotatedString.Builder()

        text.forEach { char ->
            builder.withStyle(
                style = SpanStyle(
                    color = if (isCyrillic(char)) Color.Red else textColor
                )
            ) {
                append(char)
            }
        }

        return TransformedText(builder.toAnnotatedString(), OffsetMapping.Identity)
    }
}
