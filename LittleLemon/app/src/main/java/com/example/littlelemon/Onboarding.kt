package com.example.littlelemon

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally) {

        val context = LocalContext.current

        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(0.5f)
        )

        Box(modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .background(color = Color(0xFF495E57)),
                contentAlignment = Alignment.Center,
        ) {
            Text(text = "Let's get to know you!",
                color = Color.White,
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.karla_regular))
            )
        }

        Box(modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(10.dp), contentAlignment = Alignment.CenterStart) {
            Text(text = "Personal Information",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.markazi_regular))
            )
        }

        var firstName = remember {
            mutableStateOf("")
        }
        TextField(
            value = firstName.value,
            onValueChange = {firstName.value = it},
            label = { Text(text = "First Name")},
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
        )

        var lastName = remember {
            mutableStateOf("")
        }
        TextField(
            value = lastName.value,
            onValueChange = {lastName.value = it},
            label = { Text(text = "Last Name") },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
        )

        var email = remember {
            mutableStateOf("")
        }
        TextField(
            value = email.value,
            onValueChange = {email.value = it},
            label = { Text(text = "Email") },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 5.dp)
        )
        
        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            if (firstName.value.isBlank()|| lastName.value.isBlank() || email.value.isBlank()) {
                Toast.makeText(context, "Registration unsuccessful. Please enter all data.", Toast.LENGTH_LONG).show()
            } else {
                val sharedPreferences = context.getSharedPreferences("UserPrefs", MODE_PRIVATE)
                sharedPreferences.edit()
                    .putString("firstName", firstName.value)
                    .putString("lastName", lastName.value)
                    .putString("email", email.value)
                    .commit()

                Toast.makeText(context, "Registration successful!", Toast.LENGTH_LONG).show()

                navController.navigate(Home.route)
            }
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14))
        ) {
            Text(text = "Register",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.karla_regular))
            )
        }
    }
    
}