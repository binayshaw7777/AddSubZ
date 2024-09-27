package com.binayshaw7777.addsubz.presentation.screens.shopping_calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingCalculatorViewModel @Inject constructor(): ViewModel() {

    private val _totalPrice = MutableLiveData(0f)
    val totalPrice get() = _totalPrice

    fun calculateTotalPrice(pricePerUnit: Number?, quantity: Number?) {
        if (pricePerUnit != null && quantity != null) {
            _totalPrice.value = pricePerUnit.toFloat() * (quantity.toFloat() / 1000f)
        }
    }
}