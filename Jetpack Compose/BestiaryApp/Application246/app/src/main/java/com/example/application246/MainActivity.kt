package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.application246.ui.theme.Application246Theme
import com.example.application246.ui.theme.BackgroundDarkBlue
import com.example.application246.ui.theme.BackgroundGradient
import com.example.application246.ui.theme.BorderGradient
import com.example.application246.ui.theme.PixelText

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application246Theme() {
                ApplicationScreen()
            }
        }
    }
}

@Composable
fun ApplicationScreen(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "names_screen") {
        composable(route = "names_screen") { CreaturesNamesScreen(navController = navController) }
        composable(
            route = "details_screen/{creatureId}",
            arguments = listOf(navArgument("creatureId") { type = NavType.IntType })
        ) { backStackEntry ->
            val creatureId = backStackEntry.arguments?.getInt("creatureId") ?: 0
            CreatureDetailsScreen(navController = navController, creatureId = creatureId)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreaturesNamesScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Бестиарий",
                    fontSize = 28.sp,
                    style = PixelText
                ) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF004CA8),
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = BackgroundDarkBlue,
        contentColor = Color.White
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(items = CreaturesRepository.creatures) { creature ->
                CreatureNameItem(creature = creature) {
                    navController.navigate("details_screen/${creature.id}")
                }
            }
        }
    }
}

@Composable
fun CreatureNameItem(creature: Creature, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .background(brush = BackgroundGradient, shape = RoundedCornerShape(7.dp))
            .border(
                width = 3.dp,
                brush = BorderGradient,
                shape = RoundedCornerShape(7.dp)
            )
            .padding(15.dp)
    ) {
        Text(
            text = creature.name,
            fontSize = 20.sp,
            style = PixelText
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatureDetailsScreen(navController: NavController, creatureId: Int){
    val creature = CreaturesRepository.getCreatureById(id = creatureId)

    if (creature == null){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Существо не найдено!") },
                    navigationIcon = { IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            "Back"
                        ) }
                    }
                )
            }
        ) { paddingValues ->
            Row(modifier = Modifier.padding(paddingValues)) {
                Text(text = "Произошла ошибка. Существо не найдено.")
            }
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = creature.name,
                    fontSize = 28.sp,
                    style = PixelText
                ) },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.AutoMirrored.Rounded.ArrowBack,
                        "Back"
                    ) }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF004CA8),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        containerColor = BackgroundDarkBlue,
        contentColor = Color.White
    ) { paddingValues ->
        Column(modifier = Modifier
                .padding(paddingValues)
                .padding(5.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = BackgroundGradient, shape = RoundedCornerShape(7.dp))
                    .border(
                        width = 3.dp,
                        brush = BorderGradient,
                        shape = RoundedCornerShape(7.dp)
                    )
            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.battlebg_ffvii_jungle),
                    contentDescription = "Задний фон",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(7.dp))
                )
                Image(
                    bitmap = ImageBitmap.imageResource(creature.image),
                    contentDescription = creature.name,
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .size(250.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = BackgroundGradient, shape = RoundedCornerShape(7.dp))
                    .border(
                        width = 3.dp,
                        brush = BorderGradient,
                        shape = RoundedCornerShape(7.dp)
                    )
                    .padding(15.dp)

            ) {
                Text(text = creature.description)
            }
        }
    }
}