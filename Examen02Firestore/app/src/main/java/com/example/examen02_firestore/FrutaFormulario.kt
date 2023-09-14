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

class FrutaFormulario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruta_formulario)

        val fruteriaId = intent.getStringExtra("fruteriaId") // Cambiamos el tipo de dato a String

        val botonGuardar = findViewById<Button>(R.id.btn_guardar_fruta)
        val botonCancelar = findViewById<Button>(R.id.btn_cancelar_fruta)

        botonGuardar.setOnClickListener {
            if (fruteriaId != null) {
                guardarFruta(fruteriaId)
            }
        }

        botonCancelar.setOnClickListener {
            finish()
        }
    }

    private fun guardarFruta(idF: String) {
        val nombre = obtenerTexto(R.id.txt_nombre_fruta)
        val cantidad = obtenerTextoLong(R.id.txt_cantidad)
        val precioUnitario=obtenerTextoDouble(R.id.txt_precio)
        val esOrganica = obtenerValorBooleano(R.id.chk_organica)
        val tipoFruta = obtenerTexto(R.id.txt_tipo )

        val db= Firebase.firestore
        val id= Date().time
        val conts = db.collection("frutas")

        val data1 = hashMapOf(
            "nombre" to nombre,
            "cantidad" to cantidad,
            "precioUnitario" to precioUnitario,
            "esOrganica" to esOrganica,
            "tipoFruta" to tipoFruta,
            "fruteriaId" to idF
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