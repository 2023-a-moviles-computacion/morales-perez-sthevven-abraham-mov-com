package com.example.deber02_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText


class FrutaFormularioEditar : AppCompatActivity() {
    private lateinit var botonGuardar: Button
    private lateinit var botonCancelar: Button
    private lateinit var fruta: Frutas
    private lateinit var databaseHelper: BaseDeDatosSQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruta_formulario)

        fruta = intent.getParcelableExtra(EXTRA_FRUTA)!!
        // Inicializar los campos de texto con los datos
        val nombre = findViewById<EditText>(R.id.txt_nombre_fruta)
        val cantidad = findViewById<EditText>(R.id.txt_cantidad)
        val precioUnitario = findViewById<EditText>(R.id.txt_precio)
        val esOrganica = findViewById<CheckBox>(R.id.chk_es_organica)
        val tipoFruta= findViewById<EditText>(R.id.txt_tipo)

        nombre.setText(fruta.nombreFruta)
        cantidad.setText(fruta.cantidad.toString())
        precioUnitario.setText(fruta.precioUnidad.toString())
        esOrganica.isChecked = fruta.esOrganica == true
        tipoFruta.setText(fruta.tipoFruta.toString())

        botonGuardar = findViewById(R.id.btn_guardar_fruta)
        botonCancelar = findViewById(R.id.btn_cancelar_fruta )

        botonGuardar.setOnClickListener {
            guardarFruta()
        }

        botonCancelar.setOnClickListener {
            finish()
        }
        databaseHelper = BaseDeDatosSQLite(this)
    }

    private fun guardarFruta() {
        // Actualizar los valores del continente con los datos ingresados en los campos de texto
        fruta.nombreFruta = obtenerTexto(R.id.txt_nombre_fruta)
        fruta.esOrganica = obtenerValorBooleano(R.id.chk_es_organica)
        fruta.cantidad = obtenerTextoInt(R.id.txt_cantidad)
        fruta.precioUnidad = obtenerTextoDouble(R.id.txt_precio)
        fruta.tipoFruta = obtenerTexto(R.id.txt_tipo)

        databaseHelper.updateFrutas(fruta)

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
        const val EXTRA_FRUTA = "fruta" // Mismo nombre de constante que en ListvPais
    }
}