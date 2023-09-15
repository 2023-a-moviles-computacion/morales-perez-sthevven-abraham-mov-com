package com.example.proyecto_iib_alquilerautos.ui.admin.users.clicklistener

import com.example.proyecto_iib_alquilerautos.api.types.User

interface UserClickListener {
    fun onEditCarClick(user: User)
    fun onDeleteCarClick(user: User)
}
