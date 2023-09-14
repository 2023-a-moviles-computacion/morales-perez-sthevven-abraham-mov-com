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

class FrutaFormularioEditar : AppCompatActivity() {
    private lateinit var botonGuardar: Button
    private lateinit var botonCancelar: Button
    private lateinit var fruta: Frutas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruta_formulario_editar)

        fruta = intent.getParcelableExtra(EXTRA_FRUTA)!!

        // Inicializar los campos de texto con los datos
        val nombre = findViewById<EditText>(R.id.txt_nombre_fruta)
        val cantidad = findViewById<EditText>(R.id.txt_cantidad)
        val precioUnitario = findViewById<EditText>(R.id.txt_precio)
        val esOrganica = findViewById<CheckBox>(R.id.chk_organica)
        val tipoFruta= findViewById<EditText>(R.id.txt_tipo)

        nombre.setText(fruta.nombre)
        esOrganica.isChecked = fruta.esOrganica ?:false
        cantidad.setText(fruta.cantidad.toString())
        precioUnitario.setText(fruta.precioUnitario?.toString())
        tipoFruta.setText(fruta.tipoFruta)

        botonGuardar = findViewById(R.id.btn_guardar_fruta)
        botonCancelar = findViewById(R.id.btn_cancelar_fruta )

        botonGuardar.setOnClickListener {
            guardarFruta()
        }

        botonCancelar.setOnClickListener {
            finish()
        }
    }

    private fun guardarFruta() {
        // Actualizar los valores del continente con los datos ingresados en los campos de texto
        fruta.nombre = obtenerTexto(R.id.txt_nombre_fruta)
        fruta.cantidad = obtenerTextoLong(R.id.txt_cantidad)
        fruta.precioUnitario = obtenerTextoDouble(R.id.txt_precio)
        fruta.esOrganica = obtenerValorBooleano(R.id.chk_organica)
        fruta.tipoFruta = obtenerTexto(R.id.txt_tipo)

        val db= Firebase.firestore
        val frutaId=fruta.id

        if (frutaId != null) {
            val actualizaciones = hashMapOf<String, Any?>()
            actualizaciones["nombre"] = fruta.nombre
            actualizaciones["cantidad"] = fruta.cantidad
            actualizaciones["precioUnitario"] = fruta.precioUnitario
            actualizaciones["esOrganica"] = fruta.esOrganica
            actualizaciones["tipoFruta"] = fruta.tipoFruta

            db.collection("frutas")
                .document(frutaId)
                .update(actualizaciones)
                .addOnSuccessListener { finish() }
                .addOnFailureListener { }
        }else{

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
        const val EXTRA_FRUTA = "fruta" // Mismo nombre de constante que en ListvPais
    }
}