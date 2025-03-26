package com.am.screen_mainlist.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.am.screen_mainlist.R
import com.am.localdatasource.data.database.models.CourseDBO
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ListItem(item: CourseDBO, onClickFav: () -> Unit) {

    fun formatDate(date: String): String {
        if (date.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])\$".toRegex())) {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale("ru"))
            val outputDate = inputFormat.parse(date) ?: date
            return outputFormat.format(outputDate)
        } else return date
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                shape = RoundedCornerShape(15.dp)
            ) {

                Box(modifier = Modifier.fillMaxWidth()) {

                    Image(
                        painter = painterResource(R.drawable.background1),
                        "background",
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter,
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f)),
                                contentAlignment = Alignment.Center
                            ) {

                                Icon(
                                    painterResource(
                                        if (item.hasLike) R.drawable.bookmark_fill
                                        else R.drawable.bookmark
                                    ),
                                    "bookmark",
                                    tint = if (item.hasLike) {
                                        MaterialTheme.colorScheme.primary
                                    } else MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier
                                        .clickable(onClick = onClickFav)
                                        .size(20.dp)
                                )
                            }


                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Card(
                                modifier = Modifier.height(24.dp),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(Color.Gray.copy(alpha = 0.7f))
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(horizontal = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceAround
                                    ) {
                                        Image(
                                            painterResource(R.drawable.star_filled),
                                            "taring_star",
                                            modifier = Modifier
                                                .size(20.dp)
                                                .padding(end = 6.dp)
                                        )
                                        Text(
                                            item.rate.toString(),
                                            color = Color.White
                                        )
                                    }
                                }

                            }

                            Card(
                                modifier = Modifier.height(24.dp),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(Color.Companion.Gray.copy(alpha = 0.7f))
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(horizontal = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        formatDate(item.publishDate),
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Text(
                        item.title,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Text(
                        item.text,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White.copy(0.7f)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            item.price + " ₽",
                            color = MaterialTheme.colorScheme.secondary
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                "Подробнее",
                                color = MaterialTheme.colorScheme.primary
                            )
                            Image(
                                painterResource(R.drawable.more_arrow), "arrow",
                                modifier = Modifier.size(8.dp)
                            )
                        }
                    }

                }

            }

        }
    }
}

val mockItem = CourseDBO(
    1, 1, "java", "java text", "1234", 4.5f,
    "datre", false, "2025-01-01"
)

@Preview
@Composable
fun ListItemPreview() {
    ListItem(mockItem, {})
}