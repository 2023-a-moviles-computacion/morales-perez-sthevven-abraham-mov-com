package com.example.proyecto_iib_alquilerautos.ui.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_iib_alquilerautos.MainActivity
import com.example.proyecto_iib_alquilerautos.R
import com.example.proyecto_iib_alquilerautos.ui.admin.cars.AdminCarsActivity
import com.example.proyecto_iib_alquilerautos.ui.admin.reservations.AdminReservationsActivity
import com.example.proyecto_iib_alquilerautos.ui.admin.users.AdminUsersActivity
import com.example.proyecto_iib_alquilerautos.util.UserPreferencesManager

class AdminHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)

        supportActionBar?.hide()

        val preferencesManager = UserPreferencesManager(this)

        val logoutButton: Button = findViewById(R.id.button_logout)
        logoutButton.setOnClickListener {
            preferencesManager.logout()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        verifyAdminButtons()
    }

    private fun verifyAdminButtons() {
        val reservationsButton: Button = findViewById(R.id.button_reservations)
        reservationsButton.setOnClickListener {
            startActivity(Intent(this@AdminHomeActivity, AdminReservationsActivity::class.java))
        }

        val usersButton: Button = findViewById(R.id.button_users)
        usersButton.setOnClickListener {
            startActivity(Intent(this@AdminHomeActivity, AdminUsersActivity::class.java))
        }

        val carsButton: Button = findViewById(R.id.button_cars)
        carsButton.setOnClickListener {
            startActivity(Intent(this@AdminHomeActivity, AdminCarsActivity::class.java))
        }
    }
}
