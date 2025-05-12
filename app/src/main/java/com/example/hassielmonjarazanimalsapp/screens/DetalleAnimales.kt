package com.example.hassielmonjarazanimalsapp.screens
import android.R
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.hassielmonjarazanimalsapp.components.FactItem
import com.example.hassielmonjarazanimalsapp.models.Animals
import com.example.hassielmonjarazanimalsapp.services.AnimalsService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun DetalleAnimales(
    innerPadding : PaddingValues,
    animalId: String
) {
    var BASE_URL = "https://animals.juanfrausto.com/api/"
    val scope = rememberCoroutineScope()
    var animal by remember {
        mutableStateOf<Animals?>(null)
    }
    var isLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true) {
        scope.launch {
            try {
                val retrofitBuilder = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val animalService = retrofitBuilder.create(AnimalsService::class.java)
                animal = animalService.getAnimalById(animalId)
                Log.i("Animal Detail Screen", animal.toString())
                isLoading = false
            } catch (e: Exception) {
                Log.e("ERROR", e.toString())
            }
        }
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        animal?.let { animal ->
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 25.dp)
            ) {
                Text(
                    text = animal.name,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.padding(vertical = 10.dp))

                AsyncImage(
                    model = animal.image,
                    contentDescription = animal.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(220.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.padding(vertical = 6.dp))

                Text(
                    text = animal.description,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.padding(vertical = 6.dp))

                Text(
                    text = "Hechos interesantes",
                    color = Color(0xFFAEB044),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(vertical = 6.dp))

                animal.facts.forEach { fact ->
                    FactItem(text = fact)
                    Spacer(modifier = Modifier.padding(vertical = 3.dp))
                }

                Spacer(modifier = Modifier.padding(vertical = 6.dp))

                Text(
                    text = "Galeria de imagenes",
                    color = Color(0xFFAEB044),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(vertical = 6.dp))

                LazyRow (
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(animal.imageGallery) { image ->
                        Box(
                            modifier = Modifier
                                .width(350.dp)
                                .height(300.dp)
                                .clip(RoundedCornerShape(30.dp))
                        ) {
                            AsyncImage(
                                model = image,
                                contentDescription = animal.name,
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
    }
}