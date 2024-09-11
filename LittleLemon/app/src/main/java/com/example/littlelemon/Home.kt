package com.example.littlelemon

import android.content.Context
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.room.Room
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Home(navController: NavHostController) {

    val context = LocalContext.current

    val database by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }

    // Get elements from database
    val databaseMenuItems by
        database.menuItemDao().getAll().observeAsState(emptyList())

    Column {

        Header(navController = navController)

        var searchPhrase = rememberSaveable {
            mutableStateOf("")
        }

        Hero() {
            searchPhrase.value = it
        }

        var filteredMenuItems = databaseMenuItems

        // add is not empty check here
        if (searchPhrase.value != "") {
            filteredMenuItems = databaseMenuItems.filter {
                it.title.lowercase().contains(searchPhrase.value, ignoreCase = true)
            }
        }

        MenuItems(items = filteredMenuItems)
    }
}

@Composable
fun Header(navController: NavHostController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(0.20f))
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .scale(0.75f)
                .align(alignment = Alignment.CenterVertically)
                .fillMaxWidth(0.5f)
                .weight(1f, true)
        )

        Image(painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .scale(0.75f)
                .align(alignment = Alignment.CenterVertically)
                .clickable {
                    navController.navigate(Profile.route)
                }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hero(search: (String) -> Unit) {
    Column(modifier = Modifier
        .background(color = Color(0xFF495E57))
        .padding(start = 10.dp)
    ) {
        Text(text = "Little Lemon",
            color = Color(0xFFF4CE14),
            fontSize = 40.sp,
            fontFamily = FontFamily(Font(R.font.markazi_regular)),
        )

        Row(modifier = Modifier
            .fillMaxWidth()
        ) {

            Column() {
                Text(text = "Chicago",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.markazi_regular)),
                )

                Text(text = "We are a family-owned Mediterranean restaurant, " +
                        "focused on traditional recipes served with a modern twist",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.markazi_regular)),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))

            Image(painter = painterResource(id = R.drawable.hero),
                contentDescription = "Hero",
                modifier = Modifier
                    .size(150.dp)
                    .fillMaxWidth(0.5f)
                    .clip(shape = RoundedCornerShape(30))
            )
        }

        var searchPhrase = rememberSaveable {
            mutableStateOf("")
        }

        TextField(value = searchPhrase.value,
            onValueChange = {
                searchPhrase.value = it
                search(it) },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon")
            },
            placeholder = {
                Text(text = "Enter Search Phrase")
            }
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItems(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.7f),
                    ) {
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = menuItem.title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.karla_regular))
                        )

                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = menuItem.description,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.karla_regular))
                        )

                        Text(
                            modifier = Modifier
                                .padding(5.dp),
                            text = "%.2f".format(menuItem.price),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.karla_regular))
                        )

                    }

                    GlideImage(
                        model = menuItem.image,
                        contentDescription = "",
                        modifier = Modifier
                            .size(90.dp)
                    )

                }

                Divider(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .height(1.dp),
                )
                
            }
        )
    }
}