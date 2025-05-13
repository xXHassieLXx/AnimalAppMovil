package com.example.hassielmonjarazanimalsapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.hassielmonjarazanimalsapp.models.Animals


@Composable
fun ListaAnimalesItem(
    animal : Animals,
    onClick : (Animals) -> Unit = {}
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable {
                onClick(animal)
            }
    ) {
        AsyncImage(
            model = animal.image,
            contentDescription = animal.name,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = animal.name.substringBefore(" "),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 5.dp)
        )
    }
}