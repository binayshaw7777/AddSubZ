package com.binayshaw7777.addsubz.presentation.screens.shopping_calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binayshaw7777.addsubz.presentation.components.NumberField
import com.binayshaw7777.addsubz.presentation.navigation.LocalNavHost

@Composable
fun ShoppingCalculator(
    viewModel: ShoppingCalculatorViewModel,
    modifier: Modifier = Modifier
) {

    val navController = LocalNavHost.current

    var pricePerUnit by rememberSaveable { mutableStateOf<Number?>(null) }
    var quantity by rememberSaveable { mutableStateOf<Number?>(null) }
    val totalPrice by viewModel.totalPrice.observeAsState(0f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 20.dp)
            .then(modifier)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Shopping Calculator.",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold),
            )
        }
        Spacer(modifier = Modifier.height(32.dp))

        NumberField(value = pricePerUnit, placeholder = {
            Text(text = "Price per unit")
        }) {
            pricePerUnit = it
        }

        NumberField(value = quantity, placeholder = {
            Text(text = "Quantity")
        }) {
            quantity = it
        }

        Text(text = "Total Price: ₹$totalPrice", modifier = Modifier.padding(16.dp))

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            viewModel.calculateTotalPrice(pricePerUnit, quantity)
        }, modifier = Modifier.padding(16.dp)) {
            Text(text = "Calculate")
        }
    }
}
