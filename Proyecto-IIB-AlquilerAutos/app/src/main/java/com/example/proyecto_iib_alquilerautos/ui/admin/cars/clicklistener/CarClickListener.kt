package com.example.proyecto_iib_alquilerautos.ui.admin.cars.clicklistener

import com.example.proyecto_iib_alquilerautos.api.types.Car

interface CarClickListener {
    fun onEditCarClick(car: Car)
    fun onDeleteCarClick(car: Car)
}
