package com.example.proyecto_iib_alquilerautos.ui.admin.cars.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_iib_alquilerautos.api.types.Car
import com.example.proyecto_iib_alquilerautos.databinding.RowAdminItemBinding
import com.example.proyecto_iib_alquilerautos.ui.admin.cars.clicklistener.CarClickListener
import com.example.proyecto_iib_alquilerautos.ui.admin.cars.viewholder.AdminCarsViewHolder

class AdminCarsAdapter(private val carClickListener: CarClickListener) : RecyclerView.Adapter<AdminCarsViewHolder>() {
    private var carsList: List<Car> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminCarsViewHolder {
        val item = RowAdminItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdminCarsViewHolder(item, carClickListener)
    }

    override fun getItemCount(): Int {
        return carsList.count()
    }

    override fun onBindViewHolder(holder: AdminCarsViewHolder, position: Int) {
        holder.bind(carsList[position])
    }

    fun updateCars(list: List<Car>){
        carsList = list
        notifyDataSetChanged()
    }
}
