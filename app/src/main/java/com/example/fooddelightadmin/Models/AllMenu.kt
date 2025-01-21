package com.example.fooddelightadmin.Models

data class AllMenu(
    val foodName: String? = null,
    val foodPrice: String? = null,
    internal val foodImage: String? = null,
    private val foodDescription: String? = null,
    private val foodIngredient: String? = null,
)