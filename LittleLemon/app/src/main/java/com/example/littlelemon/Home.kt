package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Home(navController: NavHostController) {
    Image(painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(0.5f)
    )

    Image(painter = painterResource(id = R.drawable.profile),
        contentDescription = "Profile",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(0.5f)
            .clickable {
                navController.navigate(Profile.route)
            }
    )
}