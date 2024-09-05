package com.example.littlelemon

import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Profile(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally) {

        val context = LocalContext.current
        val sharedPrefs = context.getSharedPreferences("UserPrefs", MODE_PRIVATE)

        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(0.5f)
        )

        Box(modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(10.dp), contentAlignment = Alignment.CenterStart) {
            Text(
                text = "Profile Information",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.markazi_regular))
            )
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), contentAlignment = Alignment.CenterStart) {
            Text(
                text = "First name",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.markazi_regular))
            )
        }

        sharedPrefs.getString("firstName", "")?.let {
            Text(
                text = it,
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.karla_regular))
            )
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), contentAlignment = Alignment.CenterStart) {
            Text(
                text = "Last name",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.markazi_regular))
            )
        }

        sharedPrefs.getString("lastName", "")?.let {
            Text(
                text = it,
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.karla_regular))
            )
        }


        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), contentAlignment = Alignment.CenterStart) {
            Text(
                text = "Email",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.markazi_regular)),
            )
        }

        sharedPrefs.getString("email", "")?.let {
            Text(
                text = it,
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.karla_regular))
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        
        Button(onClick = {
            val sharedPrefs = context.getSharedPreferences("UserPrefs", MODE_PRIVATE)
            sharedPrefs.edit()
                .remove("firstName")
                .remove("lastName")
                .remove("email")
                .commit()

            navController.navigate(Onboarding.route)

        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14))
        ) {
            Text(text = "Log out",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.karla_regular))
            )
        }
    }
}

@Preview()
@Composable
fun ProfilePreview() {
    val navControlller = rememberNavController()
    Profile(navControlller)
}