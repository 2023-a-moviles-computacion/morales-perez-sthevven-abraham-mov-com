package com.example.proyecto_iib_alquilerautos.ui.admin.reservations.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmobile.ui.admin.reservations.clicklistener.ReservationClickListener
import com.example.projectmobile.ui.admin.reservations.viewholder.AdminReservationViewHolder
import com.example.proyecto_iib_alquilerautos.api.types.Reservation
import com.example.proyecto_iib_alquilerautos.databinding.RowAdminItemBinding

class AdminReservationAdapter(private val reservationClickListener: ReservationClickListener) : RecyclerView.Adapter<AdminReservationViewHolder>() {
    private var reservationList: List<Reservation> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminReservationViewHolder {
        val item = RowAdminItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdminReservationViewHolder(item, reservationClickListener)
    }

    override fun getItemCount(): Int {
        return reservationList.count()
    }

    override fun onBindViewHolder(holder: AdminReservationViewHolder, position: Int) {
        holder.bind(reservationList[position])
    }

    fun updateReservations(list: List<Reservation>) {
        reservationList = list
        notifyDataSetChanged()
    }
}
