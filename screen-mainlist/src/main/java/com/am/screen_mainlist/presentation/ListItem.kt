package com.am.screen_mainlist.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.am.screen_mainlist.R
import com.am.screen_mainlist.data.database.models.CourseDBO

@Composable
fun ListItem  (item : CourseDBO, onClickFav : () -> Unit) {

    Card (modifier = Modifier.fillMaxWidth()
        .padding(16.dp),
        colors = CardDefaults.cardColors(Color.DarkGray),
        shape = RoundedCornerShape(15.dp)
    ){
        Column (modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start) {
            Card (modifier = Modifier.fillMaxWidth()
                .height(120.dp),
                shape = RoundedCornerShape(15.dp)) {

                Box(modifier = Modifier.fillMaxWidth()) {

                    Image(painter = painterResource(R.drawable.background1), "",
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter)

                    Column (modifier = Modifier.fillMaxHeight().padding(12.dp),
                        verticalArrangement = Arrangement.SpaceBetween) {
                        Row (modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End) {
                            Image(Icons.Default.Edit, "",
                                modifier = Modifier.clickable(onClick = onClickFav))
                        }

                        Row (modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically) {

                            Card (modifier = Modifier.height(24.dp),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(Color.Gray.copy(alpha = 0.7f))) {
                                Box(modifier = Modifier.fillMaxHeight()
                                    .padding(horizontal = 8.dp)
                                    , contentAlignment = Alignment.Center) {
                                    Row (verticalAlignment = Alignment.CenterVertically) {
                                        Image(Icons.Default.Share, "")
                                        Text(item.rate.toString(),
                                            color = Color.White)
                                    }
                                }

                            }

                            Card (modifier = Modifier.height(24.dp),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(Color.Gray.copy(alpha = 0.7f))) {
                                Box(modifier = Modifier.fillMaxHeight()
                                    .padding(horizontal = 8.dp)
                                    , contentAlignment = Alignment.Center) {
                                    Text(item.startDate,
                                        color = Color.White)
                                }

                            }
                        }
                    }
                }

            }

            Box(modifier = Modifier.fillMaxWidth().
            padding(16.dp)) {

                Column (modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)) {

                    Text(item.title,
                        fontSize = 20.sp)

                    Text(item.text,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis)

                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(item.price)

                        Row (verticalAlignment = Alignment.CenterVertically) {
                            Text("podrobnee")
                            Image(Icons.Default.MoreVert, "arrow")
                        }
                    }

                }

            }

        }
    }
}

//@Preview
//@Composable
//fun ListItemPreview () {
//    ListItem()
//}