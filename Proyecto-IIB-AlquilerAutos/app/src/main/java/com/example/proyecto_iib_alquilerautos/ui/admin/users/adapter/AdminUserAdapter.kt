package com.example.proyecto_iib_alquilerautos.ui.admin.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_iib_alquilerautos.databinding.RowAdminItemBinding
import com.example.proyecto_iib_alquilerautos.api.types.User
import com.example.proyecto_iib_alquilerautos.ui.admin.users.clicklistener.UserClickListener
import com.example.proyecto_iib_alquilerautos.ui.admin.users.viewholder.AdminUserViewHolder

class AdminUserAdapter(private val userClickListener: UserClickListener) : RecyclerView.Adapter<AdminUserViewHolder>() {
    private var usersList: List<User> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminUserViewHolder {
        val item = RowAdminItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdminUserViewHolder(item, userClickListener)
    }

    override fun getItemCount(): Int {
        return usersList.count()
    }

    override fun onBindViewHolder(holder: AdminUserViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    fun updateUsers(list: List<User>) {
        usersList = list
        notifyDataSetChanged()
    }
}
