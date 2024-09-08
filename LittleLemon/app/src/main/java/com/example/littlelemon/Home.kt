package com.example.littlelemon

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Home(navController: NavHostController) {

    Column {

        Header(navController = navController)

        Hero()

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

@Composable
fun Hero() {
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
            //.height(150.dp),
            //horizontalArrangement = Arrangement.Center
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
                    //.padding(end = 10.dp),
            )
        }
        SearchBar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(value = "",
        onValueChange = {},
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(10.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search,
                contentDescription = "Search Icon")
        }
    )
}

@Composable
fun MenuItems()
{

}