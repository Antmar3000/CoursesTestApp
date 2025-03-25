package com.am.screen_onboarding.presentation

import android.graphics.Color
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.am.screen_onboarding.data.GridItem
import kotlin.random.Random

@Composable
fun AngledCard(
    item: GridItem,
    isTilted: Boolean,
    onClick: () -> Unit
) {

    val targetRotation by remember { mutableFloatStateOf(18.0f) }

    val randomDirection = Random.nextBoolean()

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .graphicsLayer {
                rotationZ = if (isTilted) {
                    if (randomDirection) targetRotation else -targetRotation
                } else 0.0f
            }
            .shadow(20.dp,
                CircleShape),
        colors = if (isTilted) CardDefaults.cardColors(MaterialTheme.colorScheme.primary) else
            CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Box(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item.text,
                style = MaterialTheme.typography.bodyMedium,
                color = androidx.compose.ui.graphics.Color.White
            )
        }
    }
}