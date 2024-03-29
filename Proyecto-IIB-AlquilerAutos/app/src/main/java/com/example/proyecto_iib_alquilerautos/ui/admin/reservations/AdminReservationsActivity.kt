package com.example.proyecto_iib_alquilerautos.ui.admin.reservations

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_iib_alquilerautos.APICallback
import com.example.proyecto_iib_alquilerautos.api.service.APIService
import com.example.proyecto_iib_alquilerautos.api.types.APIResponse
import com.example.proyecto_iib_alquilerautos.api.types.Reservation
import com.example.proyecto_iib_alquilerautos.databinding.ActivityAdminReservationsBinding
import com.example.proyecto_iib_alquilerautos.databinding.ModalLayoutBinding
import com.example.proyecto_iib_alquilerautos.ui.admin.reservations.adapter.AdminReservationAdapter
import com.example.proyecto_iib_alquilerautos.ui.admin.reservations.clicklistener.ReservationClickListener
import com.example.proyecto_iib_alquilerautos.ui.admin.reservations.manager.ManageReservationActivity
import com.example.proyecto_iib_alquilerautos.util.UserPreferencesManager
import java.io.IOException

class AdminReservationsActivity : AppCompatActivity(), ReservationClickListener {
    private var _binding: ActivityAdminReservationsBinding? = null
    private val binding get() = _binding!!
    private val adapter = AdminReservationAdapter(this)

    private lateinit var preferencesManager: UserPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAdminReservationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferencesManager = UserPreferencesManager(this)

        supportActionBar?.hide()

        //adapter
        binding.recyclerReservations.adapter = adapter

        //layout
        binding.recyclerReservations.layoutManager = LinearLayoutManager(this)

        binding.returnButton.setOnClickListener {
            finish()
        }

        binding.addButton.setOnClickListener {
            startActivity(Intent(this, ManageReservationActivity::class.java))
        }

        binding.imageSearch.setOnClickListener {
            handleSearch()
        }
    }

    override fun onStart() {
        super.onStart()
        handleSearch()
    }

    private fun handleSearch() {
        val search = binding.editResearch.text.toString()
        getReservationsData(search)
    }

    private fun getReservationsData(search: String) {
        loading()
        val apiService = APIService(preferencesManager.getToken())
        val url = "/reservation/list?page=1&size=100&sort=ASC&search=$search"

        apiService.getData(url, object : APICallback {
            override fun onSuccess(response: APIResponse) {
                if (!response.error) {
                    val data = response.reservations
                    if (data != null) {
                        runOnUiThread {
                            adapter.updateReservations(data)
                        }
                    }

                    runOnUiThread {
                        loaded()
                    }
                } else {
                    val errorCode = response.message

                    runOnUiThread {
                        loadedWithZero()
                        Toast.makeText(
                            this@AdminReservationsActivity,
                            errorCode,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onError(error: IOException) {
                runOnUiThread {
                    loadedWithZero()
                    Toast.makeText(
                        this@AdminReservationsActivity,
                        error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun loading() {
        binding.progressBar.visibility = View.VISIBLE

        binding.notFound.visibility = View.GONE
        binding.recyclerReservations.visibility = View.GONE
    }

    private fun loaded() {
        binding.progressBar.visibility = View.GONE

        binding.notFound.visibility = View.GONE
        binding.recyclerReservations.visibility = View.VISIBLE
    }

    private fun loadedWithZero() {
        binding.progressBar.visibility = View.GONE

        binding.notFound.visibility = View.VISIBLE
        binding.recyclerReservations.visibility = View.GONE
    }

    override fun onEditCarClick(reservation: Reservation) {
        preferencesManager.saveSelectedReservation(reservation)
        startActivity(
            Intent(
                this@AdminReservationsActivity,
                ManageReservationActivity::class.java
            )
        )
    }

    override fun onDeleteCarClick(reservation: Reservation) {
        showDeleteConfirmationDialog(reservation)
    }

    private fun showDeleteConfirmationDialog(reservation: Reservation) {
        val dialog = Dialog(this)
        val dialogBinding: ModalLayoutBinding = ModalLayoutBinding.inflate(layoutInflater)
        val dialogView = dialogBinding.root
        dialog.setContentView(dialogView)

        dialogBinding.btnCancelar.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.btnConfirmar.setOnClickListener {
            deleteItem(reservation)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun deleteItem(reservation: Reservation) {
        loading()
        val apiService = APIService(preferencesManager.getToken())
        val url = "/reservation?id=${reservation.id}"

        apiService.deleteData(url, object : APICallback {
            override fun onSuccess(response: APIResponse) {
                if (!response.error) {
                    val message = response.message

                    runOnUiThread {
                        Toast.makeText(
                            this@AdminReservationsActivity,
                            message,
                            Toast.LENGTH_SHORT
                        ).show()

                        handleSearch()
                    }
                } else {
                    val errorCode = response.message

                    runOnUiThread {
                        loaded()
                        Toast.makeText(
                            this@AdminReservationsActivity,
                            errorCode,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onError(error: IOException) {
                runOnUiThread {
                    loaded()
                    Toast.makeText(
                        this@AdminReservationsActivity,
                        error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}
