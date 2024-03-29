package com.example.proyecto_iib_alquilerautos.ui.home

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_iib_alquilerautos.R
import com.example.proyecto_iib_alquilerautos.databinding.FragmentHomeBinding
import com.example.proyecto_iib_alquilerautos.ui.cars.CarsFragment
import com.example.proyecto_iib_alquilerautos.ui.formreservation.map.MapViewActivity
import com.example.proyecto_iib_alquilerautos.util.UserPreferencesManager
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment(), View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private var id: String = ""
    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel

    private val binding get() = _binding!!

    @SuppressLint("SimpleDateFormat")
    private val dateFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonWithdrawal.setOnClickListener(this)
        binding.buttonDelivery.setOnClickListener(this)
        binding.buttonContinue.setOnClickListener(this)
        binding.mapView.setOnClickListener(this)

        // Verify if a date is already selected
        verifySelectedDate()

        observeViewModel()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_withdrawal -> {
                id = view.id.toString()
                handleDate()
            }
            R.id.button_delivery -> {
                id = view.id.toString()
                handleDate()
            }
            R.id.button_continue -> {
                val dataWithdrawal = binding.buttonWithdrawal.text.toString()
                val dataDelivery = binding.buttonDelivery.text.toString()
                handleContinue(dataWithdrawal, dataDelivery)
            }
            R.id.map_view -> {
                startActivity(Intent(requireContext(), MapViewActivity::class.java))
            }
        }
    }

    override fun onDateSet(v: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val preferencesManager = UserPreferencesManager(requireContext())

        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dueDate = dateFormat.format(calendar.time)
        if (id == R.id.button_withdrawal.toString()) {
            preferencesManager.saveWithdrawDate(dueDate)
            binding.buttonWithdrawal.text = dueDate
        } else if (id == R.id.button_delivery.toString()) {
            preferencesManager.saveDeliveryDate(dueDate)
            binding.buttonDelivery.text = dueDate
        }
    }

    private fun handleDate() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(requireContext(), this, year, month, day).show()
    }

    private fun observeViewModel() {
        val textViewWithdrawal: TextView = binding.buttonWithdrawal
        homeViewModel.dataWithdrawal.observe(viewLifecycleOwner) {
            textViewWithdrawal.text = it
        }

        val textViewDelivery: TextView = binding.buttonDelivery
        homeViewModel.dataDelivery.observe(viewLifecycleOwner) {
            textViewDelivery.text = it
        }
    }

    private fun handleContinue(dataWithdrawal: String, dataDelivery: String) {
        if (dataWithdrawal == "ESCOLHA UMA DATA" || dataDelivery == "ESCOLHA UMA DATA") {
            Toast.makeText(
                requireContext(),
                "As datas devem ser definidas!",
                Toast.LENGTH_SHORT,
            ).show()
        } else {
            val fragment = CarsFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun verifySelectedDate() {
        val preferencesManager = UserPreferencesManager(requireContext())

        val wDate = preferencesManager.getWithdrawDate()
        if (wDate != null) {
            binding.buttonWithdrawal.text = wDate
        }

        val dDate = preferencesManager.getDeliveryDate()
        if (dDate != null) {
            binding.buttonDelivery.text = dDate
        }
    }
}
