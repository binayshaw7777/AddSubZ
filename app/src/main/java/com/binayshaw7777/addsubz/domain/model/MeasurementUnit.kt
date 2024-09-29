package com.binayshaw7777.addsubz.domain.model

enum class MeasurementUnit(
    val conversionToBase: Float, // Conversion factor to base unit (grams or liters)
    val type: UnitType,          // Type: Weight or Volume
    val shortName: String,       // Short abbreviation
    val fullName: String         // Full display name
) {
    // Weight units (base: grams)
    MILLIGRAM(0.001f, UnitType.WEIGHT, "mg", "Milligrams"),
    GRAM(1f, UnitType.WEIGHT, "gm", "Grams"),
    KILOGRAM(1000f, UnitType.WEIGHT, "kg", "Kilograms"),
    POUND(453.592f, UnitType.WEIGHT, "lb", "Pounds"),
    OUNCE(28.3495f, UnitType.WEIGHT, "oz", "Ounces"),

    // Volume units (base: liters)
    MILLILITER(0.001f, UnitType.VOLUME, "ml", "Milliliters"),
    LITER(1f, UnitType.VOLUME, "L", "Liters"),
    GALLON(3.78541f, UnitType.VOLUME, "gal", "Gallons"),
    CUP(0.24f, UnitType.VOLUME, "cup", "Cups");

    fun convertToBase(amount: Float): Float {
        return amount * conversionToBase
    }

    enum class UnitType {
        WEIGHT, VOLUME
    }
}
