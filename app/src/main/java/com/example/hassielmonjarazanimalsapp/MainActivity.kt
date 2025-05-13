package com.example.hassielmonjarazanimalsapp

import DetalleAmbientes
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hassielmonjarazanimalsapp.screens.ListaAnimales
import com.example.hassielmonjarazanimalsapp.screens.DetalleAnimales
import com.example.hassielmonjarazanimalsapp.screens.ListaAmbientes
import com.example.hassielmonjarazanimalsapp.ui.theme.HassielMonjarazAnimalsAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HassielMonjarazAnimalsAppTheme {
                var selectedScreen by remember {
                    mutableStateOf("animal-list")
                }
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                        .background(Color(0xFF0C3611)),
                    contentColor = Color.Transparent,
                    containerColor = Color.Transparent,
                    bottomBar = {
                        Box (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp).padding(horizontal = 40.dp)
                                .background(Color(0xFFF0F174), shape = RoundedCornerShape(40.dp))

                        ) {
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Absolute.SpaceBetween
                            ) {
                                NavigationBarItem(
                                    selected = selectedScreen == "animal-list",
                                    onClick = {
                                        selectedScreen = "animal-list"
                                        navController.navigate("animal-list")
                                    },
                                    icon = {
                                        Row (
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.ggg),
                                                contentDescription = "Animals",
                                                modifier = Modifier.padding(end = 8.dp).size(32.dp),
                                                colorFilter = ColorFilter.tint(Color.Black)
                                            )
                                            Text(
                                                text = "Inicio",
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        indicatorColor = Color.Transparent
                                    )
                                )
                                NavigationBarItem(
                                    selected = selectedScreen == "environment-list",
                                    onClick = {
                                        selectedScreen = "environment-list"
                                        navController.navigate("environment-list")
                                    },
                                    icon = {
                                        Row (
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.ambientenav),
                                                contentDescription = "Environments",
                                                modifier = Modifier.padding(end = 8.dp).size(32.dp),
                                                colorFilter = ColorFilter.tint(Color.Black)
                                            )
                                            Text(
                                                text = "Ambientes",
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        indicatorColor = Color.Transparent
                                    )
                                )
                            }
                        }

                    }

                ) { innerPadding ->
                    NavHost(
                        navController = navController, startDestination = "animal-list"
                    ) {
                        composable(route = "animal-list") {
                            ListaAnimales(
                                innerPadding = innerPadding, navController = navController
                            )
                        }
                        composable(
                            route = "animal-list/{animalId}",
                            arguments = listOf(
                                navArgument("animalId") {
                                    type = NavType.StringType
                                    nullable = false
                                }
                            )
                        ) {
                            val animalId = it.arguments?.getString("animalId") ?: ""
                            DetalleAnimales(
                                innerPadding = innerPadding,
                                animalId = animalId
                            )
                        }
                        composable(route = "environment-list") {
                            ListaAmbientes(
                                innerPadding = innerPadding, navController = navController
                            )
                        }
                        composable(
                            route = "environment-list/{environmentId}",
                            arguments = listOf(
                                navArgument("environmentId") {
                                    type = NavType.StringType
                                    nullable = false
                                }
                            )
                        ) {
                            val environmentId = it.arguments?.getString("environmentId") ?: ""
                            DetalleAmbientes(
                                innerPadding = innerPadding,
                                environmentId = environmentId,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}