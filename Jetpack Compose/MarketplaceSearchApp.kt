// package com.example.application246

import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentSearchState = remember { mutableStateOf(SearchState()) }

            val carList = arrayOf(
                Car("BMW", 1500.0),
                Car("Mercedes", 1800.0),
                Car("Toyota", 1600.0),
                Car("Ford", 1700.0),
                Car("Lada", 1100.0)
            )

            NavHost(
                navController = navController,
                startDestination = "home"
            ){
                composable("home") { HomeScreen(navController, carList, currentSearchState) }
                composable("search") {
                    SearchScreen(navController = navController, currentState = currentSearchState)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    carList: Array<Car>,
    searchState: MutableState<SearchState>
){
    val filteredCars = remember { derivedStateOf {
        carList.filter { car ->
            val brandMatch = (searchState.value.brand.isEmpty()) or
                    (car.brand.contains(searchState.value.brand, true))
            val minCost = searchState.value.minCost.toDoubleOrNull() ?: 0.0
            val maxCost = searchState.value.maxCost.toDoubleOrNull() ?: Double.MAX_VALUE
            val costMatch = (car.price >= minCost) and (car.price <= maxCost)

            brandMatch and (costMatch)
        }
    } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Car list") },
                actions = {
                    IconButton(onClick = { navController.navigate("search") }) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search"
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(filteredCars.value) { car ->
                SingleCar(car)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController, currentState: MutableState<SearchState>){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Car search") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = currentState.value.brand,
                onValueChange = {currentState.value = currentState.value.copy(brand = it)},
                placeholder = { Text(text = "Brand") },
                trailingIcon = {
                    IconButton(
                        onClick = { currentState.value = currentState.value.copy(brand = "") }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = currentState.value.minCost,
                    onValueChange = { currentState.value = currentState.value.copy(minCost = it) },
                    prefix = { Text(text = "cost from") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = currentState.value.maxCost,
                    onValueChange = { currentState.value = currentState.value.copy(maxCost = it) },
                    prefix = { Text(text = "to") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Button(onClick = { navController.navigate("home") }) {
                Text(text = "Confirm")
            }
        }
    }
}

@Composable
fun SingleCar(car: Car) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            bitmap = ImageBitmap.imageResource(car.image),
            contentDescription = car.brand,
            modifier = Modifier.size(100.dp)
        )
        Text(text = car.brand, modifier = Modifier.weight(1f))
        Text(text = "${car.price}$")
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun PreviewFunction(){
    SingleCar(Car("Tesla", 2500.0))
}

data class SearchState(
    val brand: String = "",
    val minCost: String = "",
    val maxCost: String = ""
)

data class Car(
    val brand: String,
    val price: Double,
    val image: Int = R.drawable.car_placeholder
)
