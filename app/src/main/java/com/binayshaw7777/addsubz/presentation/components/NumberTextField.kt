package com.binayshaw7777.addsubz.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.binayshaw7777.addsubz.presentation.theme.light12

@Composable
fun NumberField(
    value: Number?,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    textFieldHeight: (Dp) -> Unit = {},
    onNumberChange: (Number?) -> Unit
) {
    val localDensity = LocalDensity.current

    val number = remember { mutableStateOf(value) }
    val textValue = remember(value != number.value) {
        number.value = value
        mutableStateOf(value?.toDouble()?.let {
            if (it % 1.0 == 0.0) {
                it.toInt().toString()
            } else {
                it.toString()
            }
        } ?: "")
    }

    val numberRegex = remember { "[-]?[\\d]*[.]?[\\d]*".toRegex() }
    // for no negative numbers use "[\d]*[.]?[\d]*"


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.then(modifier)
    ) {
        label?.let {
            Text(
                text = label,
                modifier = Modifier.padding(bottom = 8.dp),
                style = light12,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        OutlinedTextField(
            value = textValue.value,
            onValueChange = {
                if (numberRegex.matches(it)) {
                    textValue.value = it
                    number.value = it.toDoubleOrNull()
                    onNumberChange(number.value)
                }
            },
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(10.dp),  // Rounded corners of 10dp
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier
                .onGloballyPositioned {
                    textFieldHeight(with(localDensity) { it.size.height.toDp() })
                }
        )

    }

}