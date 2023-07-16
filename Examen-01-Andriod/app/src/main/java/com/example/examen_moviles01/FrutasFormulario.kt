package com.example.examen_moviles01

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi

class FrutasFormulario : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    var arregloF = BaseDatosMemoria.listaFrutas

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frutas_formulario)

        val fruteriaSeleccionada = intent.getIntExtra("idFruteria", -1)
        var idFruteria = findViewById<EditText>(R.id.editText_id_fruta)
        idFruteria.setText(fruteriaSeleccionada.toString())
        idFruteria.isEnabled = false

        Log.i("TAG", "Formulario de Frutas en la Fruteria ${fruteriaSeleccionada}")

        val botonAceptarCreacionFrutas = findViewById<Button>(R.id.btn_aceptar_frutas_formulario)
        botonAceptarCreacionFrutas.setOnClickListener {
            var id = findViewById<EditText>(R.id.editText_id_fruta).text.toString().toInt()
            var nombre = findViewById<EditText>(R.id.editText_nombre_fruta).text.toString()
            var cantidad = findViewById<EditText>(R.id.editText_cantidad_fruta).text.toString().toInt()
            var precioUnidad = findViewById<EditText>(R.id.editText_precio_unidad_fruta).text.toString().toDouble()
            var esOrganico = findViewById<EditText>(R.id.editText_es_organico_fruta).text.toString().toBoolean()
            var tipoFruta = findViewById<EditText>(R.id.editText_tipo_fruta).text.toString()

            Log.i("TAG", "Formulario de Frutas en la Fruteria ${id}")
            arregloF.add(Frutas(id,nombre,cantidad,precioUnidad,esOrganico,tipoFruta))
            finish()
        }
    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this, clase);
        startActivity(intent);
    }
}