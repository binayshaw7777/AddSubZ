package com.binayshaw7777.addsubz.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun NumberField(
    value: Number?,
    placeholder: @Composable (() -> Unit)? = null,
    onNumberChange: (Number?) -> Unit,
) {
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

    TextField(
        value = textValue.value,
        onValueChange = {
            if (numberRegex.matches(it)) {
                textValue.value = it
                number.value = it.toDoubleOrNull()
                onNumberChange(number.value)
            }
        },
        placeholder = placeholder,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}