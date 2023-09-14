package com.example.examen02_firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class FruteriaFormulario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruteria_formulario)

        val botonGuardar = findViewById<Button>(R.id.btn_guardar_fruteria)
        val botonCancelar = findViewById<Button>(R.id.btn_cancelar_fruteria)

        botonGuardar.setOnClickListener {
            guardarFruteria()
        }

        botonCancelar.setOnClickListener {
            finish()
        }
    }

    private fun guardarFruteria() {
        val nombre = obtenerTexto(R.id.txt_nombre_fruteria)
        val direccion = obtenerTexto(R.id.txt_direccion)
        val numeroEmpleados=obtenerTextoLong(R.id.txt_empleados)
        val estaAbierto = obtenerValorBooleano(R.id.chk_abierto)
        val ingresosSemanales = obtenerTextoDouble(R.id.txt_ingresos)

        val db= Firebase.firestore
        val id= Date().time
        val conts = db.collection("fruterias")

        val data1 = hashMapOf(
            "nombre" to nombre,
            "direccion" to direccion,
            "numeroEmpleados" to numeroEmpleados,
            "estaAbiero" to estaAbierto,
            "ingresosSemanales" to ingresosSemanales
        )

        conts.document(id.toString()).set(data1)
            .addOnSuccessListener {
                finish()
            }
            .addOnFailureListener {  }

        conts.document(id.toString()).update("id",id.toString())
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

}