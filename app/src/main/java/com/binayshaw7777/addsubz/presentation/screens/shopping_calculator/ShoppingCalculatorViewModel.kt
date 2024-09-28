package com.binayshaw7777.addsubz.presentation.screens.shopping_calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binayshaw7777.addsubz.domain.model.MeasurementUnit
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingCalculatorViewModel @Inject constructor(): ViewModel() {

    private val _totalPrice = MutableLiveData(0f)
    val totalPrice get() = _totalPrice

    fun calculateTotalPrice(pricePerUnit: Number?, quantity: Number?, quantityUnit: MeasurementUnit, measurementUnit: MeasurementUnit) {
        if (pricePerUnit != null && quantity != null) {
            println("PricePerUnit: ${pricePerUnit.toFloat()}")
            println("MeasuringUnit: per ${measurementUnit.fullName}")
            println("Quantity: ${quantity.toFloat()}")
            println("QuantityUnit: ${quantityUnit.fullName}")
            println("PriceConversion: ${(pricePerUnit.toFloat() / measurementUnit.conversionToBase)}")
            println("QuantityConversion: ${(quantity.toFloat() * quantityUnit.conversionToBase)}")
            _totalPrice.value = (pricePerUnit.toFloat() / measurementUnit.conversionToBase) * (quantity.toFloat() * quantityUnit.conversionToBase)
        }
    }
}