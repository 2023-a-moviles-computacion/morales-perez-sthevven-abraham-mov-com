package com.example.deber02_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText


class FrutaFormulario: AppCompatActivity() {
    private lateinit var arreglo: MutableList<Frutas>
    private lateinit var databaseHelper: BaseDeDatosSQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruta_formulario)

        databaseHelper = BaseDeDatosSQLite(this)
        val fruteriaId = intent.getIntExtra("fruteriaId", 0)
        arreglo = databaseHelper.getFrutasByFruteria(fruteriaId) as MutableList<Frutas>
        val botonGuardar = findViewById<Button>(R.id.btn_guardar_fruta)
        val botonCancelar = findViewById<Button>(R.id.btn_cancelar_fruta)
        botonGuardar
            .setOnClickListener {
                guardarFruta(fruteriaId)
            }
        botonCancelar
            .setOnClickListener {
                finish()
            }
    }

    private fun guardarFruta(idF: Int) {
        val nombre = obtenerTexto(R.id.txt_nombre_fruta)
        val cantidad = obtenerTextoInt(R.id.txt_cantidad)
        val precioUnitario = obtenerTextoDouble(R.id.txt_precio)
        val esOrganica=obtenerValorBooleano(R.id.chk_es_organica)
        val tipoFruta = obtenerTexto(R.id.txt_tipo)

        val nuevaFruta = Frutas(0,"", 0, 0.0, false,"")
        nuevaFruta.crearFrutas(nombre,cantidad,precioUnitario,esOrganica,tipoFruta)

        val id = databaseHelper.insertFrutas(nuevaFruta)
        nuevaFruta.codigoFruteria= id.toInt()
        arreglo.add(nuevaFruta)

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