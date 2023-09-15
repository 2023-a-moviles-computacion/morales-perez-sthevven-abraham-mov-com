package com.example.proyecto_iib_alquilerautos.ui.admin.cars.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_iib_alquilerautos.R
import com.example.proyecto_iib_alquilerautos.api.types.Car
import com.example.proyecto_iib_alquilerautos.databinding.RowAdminItemBinding
import com.example.proyecto_iib_alquilerautos.ui.admin.cars.clicklistener.CarClickListener

class AdminCarsViewHolder(private val bind: RowAdminItemBinding, private val carClickListener: CarClickListener): RecyclerView.ViewHolder(bind.root) {
    fun bind(car: Car) {
        val nameCar = itemView.context.getString(R.string.car_name, car.brand, car.model)

        bind.itemContent.text = nameCar

        bind.arrowRight.setOnClickListener {
            carClickListener.onEditCarClick(car)
        }

        bind.trash.setOnClickListener {
            carClickListener.onDeleteCarClick(car)
        }
    }
}
