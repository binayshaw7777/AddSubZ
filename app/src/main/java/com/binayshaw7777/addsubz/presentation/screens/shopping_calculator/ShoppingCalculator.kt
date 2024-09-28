package com.binayshaw7777.addsubz.presentation.screens.shopping_calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binayshaw7777.addsubz.domain.model.MeasurementUnit
import com.binayshaw7777.addsubz.presentation.components.NumberField
import com.binayshaw7777.addsubz.presentation.navigation.LocalNavHost

@Composable
fun ShoppingCalculator(
    viewModel: ShoppingCalculatorViewModel,
    modifier: Modifier = Modifier
) {

    val navController = LocalNavHost.current
    val context = LocalContext.current

    var textFieldHeightDp by remember {
        mutableStateOf(0.dp)
    }

    var pricePerUnit by rememberSaveable { mutableStateOf<Number?>(null) }
    var quantity by rememberSaveable { mutableStateOf<Number?>(null) }
    val totalPrice by viewModel.totalPrice.observeAsState(0f)

    var measurementUnit by rememberSaveable { mutableStateOf(MeasurementUnit.GRAM) }
    var quantityUnit by rememberSaveable { mutableStateOf(MeasurementUnit.GRAM) }

    var isMeasurementUnitOptionsEnabled by remember { mutableStateOf(false) }
    var isQuantityUnitOptionsEnabled by remember { mutableStateOf(false) }


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

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(16.dp)
        ) {
            NumberField(
                value = pricePerUnit,
                placeholder = {
                    Text(text = "eg. 63")
                },
                label = "Price per unit",
                textFieldHeight = {
                    textFieldHeightDp = it
                },
                modifier = Modifier.weight(2f)
            ) {
                pricePerUnit = it
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(textFieldHeightDp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .weight(1f)
                    .clickable { isMeasurementUnitOptionsEnabled = true },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "per ${measurementUnit.shortName}")

                DropdownMenu(
                    expanded = isMeasurementUnitOptionsEnabled,
                    onDismissRequest = { isMeasurementUnitOptionsEnabled = false }
                ) {
                    MeasurementUnit.entries.forEach {
                        DropdownMenuItem(
                            text = { Text(it.fullName) },
                            onClick = {
                                measurementUnit = it
                                isMeasurementUnitOptionsEnabled = false
                            }
                        )
                    }
                }
            }

        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(16.dp)
        ) {
            NumberField(
                value = quantity, placeholder = {
                    Text(text = "eg. 562")
                },
                label = "Quantity",
                modifier = Modifier.weight(2f)
            ) {
                quantity = it
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(textFieldHeightDp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .weight(1f)
                    .clickable { isQuantityUnitOptionsEnabled = true },
                contentAlignment = Alignment.Center
            ) {
                Text(text = quantityUnit.fullName)

                DropdownMenu(
                    expanded = isQuantityUnitOptionsEnabled,
                    onDismissRequest = { isQuantityUnitOptionsEnabled = false }
                ) {
                    MeasurementUnit.entries.forEach {
                        DropdownMenuItem(
                            text = { Text(it.fullName) },
                            onClick = {
                                quantityUnit = it
                                isQuantityUnitOptionsEnabled = false
                            }
                        )
                    }
                }
            }
        }

        Text(text = "Total Price: ₹$totalPrice", modifier = Modifier.padding(16.dp))

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            viewModel.calculateTotalPrice(pricePerUnit, quantity, quantityUnit, measurementUnit)
        }, modifier = Modifier.padding(16.dp)) {
            Text(text = "Calculate")
        }
    }
}
