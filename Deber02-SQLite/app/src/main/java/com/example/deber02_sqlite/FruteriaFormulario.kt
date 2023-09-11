package com.example.deber02_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*

class FruteriaFormulario: AppCompatActivity() {
    private lateinit var arreglo: MutableList<Fruterias>
        private lateinit var databaseHelper: BaseDeDatosSQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruteria_formulario)

        databaseHelper = BaseDeDatosSQLite(this)
        arreglo = databaseHelper.getAllFruterias() as MutableList<Fruterias>

        val botonGuardar = findViewById<Button>(R.id.btn_guardar_fruteria)
        val botonCancelar = findViewById<Button>(R.id.btn_cancelar_fruteria)
        botonGuardar
            .setOnClickListener {
                guardarFruteria()
            }
        botonCancelar
            .setOnClickListener {
                finish()
            }
    }

    private fun guardarFruteria() {
        val nombre = obtenerTexto(R.id.txt_nombre_fruteria)
        val direccion = obtenerTexto(R.id.txt_direccion)
        val cantidadEmpleados=obtenerTextoInt(R.id.txt_empleados)
        val estaAbierto = obtenerValorBooleano(R.id.chk_abierto)
        val ingresosSemanales = obtenerTextoDouble(R.id.txt_ingresos)

        val nuevaFruterias = Fruterias(0,"", "", 0, false, 0.0)
        nuevaFruterias.crearFruteria(nombre,direccion,cantidadEmpleados,estaAbierto,ingresosSemanales)

        val id = databaseHelper.insertFruteria(nuevaFruterias)
        nuevaFruterias.id = id.toInt()
        arreglo.add(nuevaFruterias)  // Agregar el nuevo continente a la lista existente en LvContineete
        finish()
    }



    private fun obtenerTexto(id: Int): String {
        val editText = findViewById<EditText>(id)
        return editText.text.toString()
    }
    private fun obtenerTextoInt(id: Int): Int {
        val editText = findViewById<EditText>(id)
        val texto = editText.text.toString()
        return if (texto.isNotEmpty()) texto.toInt() else 0
    }

    private fun obtenerTextoDouble(id: Int): Double {
        val editText = findViewById<EditText>(id)
        val texto = editText.text.toString()
        return if (texto.isNotEmpty()) texto.toDouble() else 0.0
    }

    private fun obtenerValorBooleano(id: Int): Boolean {
        val checkBox = findViewById<CheckBox>(id)
        return checkBox.isChecked
    }

}