package com.example.deber02_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class FruteriaFormularioEditar : AppCompatActivity() {
    private lateinit var botonGuardar: Button
    private lateinit var botonCancelar: Button
    private lateinit var fruteria: Fruterias
    private lateinit var databaseHelper: BaseDeDatosSQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruteria_formulario)

        fruteria =
            intent.getParcelableExtra(EXTRA_FRUTERIA)!!  // para obtener un objeto Parcelable de un Intent. El operador !!  para forzar el objeto obtenido a ser no nulo,
        // Inicializar los campos de texto con los datos del continente
        val nombre = findViewById<EditText>(R.id.txt_nombre_fruteria)
        val direccion = findViewById<EditText>(R.id.txt_direccion)
        val empleados = findViewById<EditText>(R.id.txt_empleados)
        val estaAbierto = findViewById<CheckBox>(R.id.chk_abierto)
        val ingresos = findViewById<EditText>(R.id.txt_ingresos)

        nombre.setText(fruteria.nombre)
        estaAbierto.isChecked = fruteria.estaAbierto==true
        direccion.setText(fruteria.direccion)
        empleados.setText(fruteria.cantidadEmpleados.toString())
        ingresos.setText(fruteria.ingresosSemanales.toString())

        botonGuardar = findViewById(R.id.btn_guardar_fruteria)
        botonCancelar = findViewById(R.id.btn_cancelar_fruteria)

        botonGuardar.setOnClickListener {
            guardarFruteria()
        }

        botonCancelar.setOnClickListener {
            finish()
        }
        databaseHelper = BaseDeDatosSQLite(this)
    }

    private fun guardarFruteria() {
        // Actualizar los valores del continente con los datos ingresados en los campos de texto
        fruteria.nombre = obtenerTexto(R.id.txt_nombre_fruteria)
        fruteria.direccion = obtenerTexto(R.id.txt_direccion)
        fruteria.cantidadEmpleados = obtenerTextoInt(R.id.txt_empleados)
        fruteria.estaAbierto = obtenerValorBooleano(R.id.chk_abierto)
        fruteria.ingresosSemanales = obtenerTextoDouble(R.id.txt_ingresos)

        databaseHelper.updateFruteria(fruteria)

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

    companion object {
         const val EXTRA_FRUTERIA=
            "fruteria" // Mismo nombre de constante que en ListvContinente
    }
}