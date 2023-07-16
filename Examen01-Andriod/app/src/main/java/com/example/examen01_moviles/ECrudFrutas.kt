package com.example.examen01_moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class ECrudFrutas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_frutas)

        // Obtener el consolaId de los extras del intent
        val fruteriaId = intent.getIntExtra("FruteriaID", -1)

        val botonCrearBDD = findViewById<Button>(R.id.btn_agregar_fruta)
        botonCrearBDD
            .setOnClickListener {
                val nombreFruta = findViewById<EditText>(R.id.input_nombre_fruta)
                val cantidad = findViewById<EditText>(R.id.input_cantidad)
                val precioUnidad = findViewById<EditText>(R.id.input_precio_unidad)
                val esOrganica = findViewById<EditText>(R.id.input_es_organica)
                val tipoFruta = findViewById<EditText>(R.id.input_tipo_fruta)
                EBaseDeDatos.frutasTablas!!.crearFrutas(
                    nombreFruta.text.toString(),
                    cantidad.text.toString().toInt(),
                    precioUnidad.text.toString().toDouble(),
                    esOrganica.text.toString(),
                    tipoFruta.text.toString(),
                    fruteriaId.toString().toInt()
                )

                // Notificar al adaptador que los datos han cambiado
                actualizarListaFrutas()
            }



    }

    private fun actualizarListaFrutas() {
        val listViewFrutas = findViewById<ListView>(R.id.lv_frutas)
        val adaptador = listViewFrutas.adapter as ArrayAdapter<BFrutas>?
        adaptador?.notifyDataSetChanged()
    }

}