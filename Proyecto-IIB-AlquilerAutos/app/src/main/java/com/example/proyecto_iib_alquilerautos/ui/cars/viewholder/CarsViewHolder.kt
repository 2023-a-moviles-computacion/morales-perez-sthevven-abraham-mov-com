package com.example.proyecto_iib_alquilerautos.ui.cars.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_iib_alquilerautos.R
import com.example.proyecto_iib_alquilerautos.databinding.RowCarBinding
import com.example.proyecto_iib_alquilerautos.api.types.Car

class CarsViewHolder(private val bind: RowCarBinding, private val onItemClick: (Car) -> Unit) : RecyclerView.ViewHolder(bind.root) {
    fun bind(car: Car) {
        val nameCar = itemView.context.getString(R.string.car_name, car.brand, car.model)
        val priceCar = itemView.context.getString(R.string.car_price, "R$", car.value.toString())

        bind.textNameCarRow.text = nameCar
        bind.textPriceCar.text = priceCar

        bind.root.setOnClickListener {
            onItemClick(car)
        }
    }
}
