package com.example.proyecto_iib_alquilerautos.api.types

data class Car(
    val id: Int,
    val brand: String,
    val model: String,
    val color: String,
    val plate: String,
    val value: Float
)
