package com.example.examen01_moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class ECrudFruteria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_fruteria)

        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_fruteria)
        botonCrearBDD
            .setOnClickListener {
                val nombre = findViewById<EditText>(R.id.input_nombre)
                val direccion = findViewById<EditText>(R.id.input_direccion)
                val cantidadEmpleados = findViewById<EditText>(R.id.input_cantidad_empleados)
                val estaAbierto = findViewById<EditText>(R.id.input_esta_abierto)
                val ingresosSemanles = findViewById<EditText>(R.id.input_ingresos_semanales)
                EBaseDeDatos.fruteriaTablas!!.crearFruteria(
                    nombre.text.toString(),
                    direccion.text.toString(),
                    cantidadEmpleados.text.toString().toInt(),
                    estaAbierto.text.toString(),
                    ingresosSemanles.text.toString().toDouble()
                )

                // Notificar al adaptador que los datos han cambiado
                actualizarListaFruterias()
            }



    }

    private fun actualizarListaFruterias() {
        val listViewFruterias = findViewById<ListView>(R.id.lv_fruterias)
        val adaptador = listViewFruterias.adapter as ArrayAdapter<BFruteria>?
        adaptador?.notifyDataSetChanged()
    }
}