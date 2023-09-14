package com.example.examen02_firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class FruteriaFormularioEditar : AppCompatActivity() {
    private lateinit var botonGuardar: Button
    private lateinit var botonCancelar: Button
    private lateinit var fruteria: Fruterias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruteria_formulario_editar)

        fruteria = intent.getParcelableExtra(EXTRA_FRUTERIA)!!  // para obtener un objeto Parcelable de un Intent. El operador !!  para forzar el objeto obtenido a ser no nulo,

        // Inicializar los campos de texto con los datos de la fruteria
        val nombre = findViewById<EditText>(R.id.txt_nombre_fruteria)
        val direccion = findViewById<EditText>(R.id.txt_direccion)
        val cantidadEmpleados = findViewById<EditText>(R.id.txt_empleados)
        val estaAbierto = findViewById<CheckBox>(R.id.chk_abierto)
        val ingresosSemanales = findViewById<EditText>(R.id.txt_ingresos)

        nombre.setText(fruteria.nombre)
        direccion.setText(fruteria.direccion)
        cantidadEmpleados.setText(fruteria.numeroEmpleados.toString())
        estaAbierto.isChecked = fruteria.estaAbierto ?:false
        ingresosSemanales.setText(fruteria.ingresosSemanales?.toString())

        botonGuardar = findViewById(R.id.btn_guardar_fruteria)
        botonCancelar = findViewById(R.id.btn_cancelar_fruteria)

        botonGuardar.setOnClickListener {
            guardarFruteria()
        }

        botonCancelar.setOnClickListener {
            finish()
        }
    }

    private fun guardarFruteria() {
        // Actualizar los valores de la fruteria con los datos ingresados en los campos de texto
        fruteria.nombre = obtenerTexto(R.id.txt_nombre_fruteria)
        fruteria.direccion = obtenerTexto(R.id.txt_direccion)
        fruteria.numeroEmpleados = obtenerTextoLong(R.id.txt_empleados)
        fruteria.estaAbierto = obtenerValorBooleano(R.id.chk_abierto)
        fruteria.ingresosSemanales = obtenerTextoDouble(R.id.txt_ingresos)

        val db= Firebase.firestore
        val fruteriaId = fruteria.id

        if (fruteriaId != null) {
            // Crear un mapa de datos solo con los campos que deseas actualizar
            val actualizaciones = hashMapOf<String, Any?>()
            actualizaciones["nombre"] = fruteria.nombre
            actualizaciones["direccion"] = fruteria.direccion
            actualizaciones["numeroEmpelados"] = fruteria.numeroEmpleados
            actualizaciones["estaAbierto"] = fruteria.estaAbierto
            actualizaciones["ingresosSemanales"] = fruteria.ingresosSemanales

            db.collection("fruterias")
                .document(fruteriaId)
                .update(actualizaciones)
                .addOnSuccessListener {
                    // La actualización fue exitosa
                    finish()
                }
                .addOnFailureListener { e ->
                    // Ocurrió un error durante la actualización
                    // Manejar el error según sea necesario
                }

        } else {
            // Manejar el caso en el que el ID del documento sea nulo
        }
    }

    private fun obtenerTexto(id: Int): String {
        val editText = findViewById<EditText>(id)
        return editText.text.toString()
    }
    private fun obtenerTextoLong(id: Int): Long {
        val editText = findViewById<EditText>(id)
        val texto = editText.text.toString()
        return if (texto.isNotEmpty()) texto.toLong() else 0L
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
        const val EXTRA_FRUTERIA = "fruteria" // Mismo nombre de constante que en ListvContinente
    }
}