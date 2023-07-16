package com.example.examen_moviles01

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi

class FrutasFormularioEditar: AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    var arregloF = BaseDatosMemoria.listaFrutas

    @SuppressLint("CutPasteId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frutas_formulario_editar)


        val frutaSeleccionado = intent.getSerializableExtra("Fruta") as Frutas

        val id = findViewById<EditText>(R.id.editText_id_fruta_Editar)
        val nombreFruta = findViewById<EditText>(R.id.editText_nombre_fruta_Editar)
        val cantidad = findViewById<EditText>(R.id.editText_cantidad_fruta_Editar)
        val precioUnidad = findViewById<EditText>(R.id.editText_precio_unidad_fruta_Editar)
        val esOrganico = findViewById<EditText>(R.id.editText_es_organico_fruta_Editar)
        val tipoFruta = findViewById<EditText>(R.id.editText_tipo_fruta_Editar)

        id.setText(frutaSeleccionado.codigoFruteria.toString())
        nombreFruta.setText(frutaSeleccionado.nombreFruta.toString())
        cantidad.setText(frutaSeleccionado.cantidad.toString())
        precioUnidad.setText(frutaSeleccionado.precioUnidad.toString())
        esOrganico.setText(frutaSeleccionado.esOrganica.toString())
        tipoFruta.setText(frutaSeleccionado.tipoFruta.toString())

        id.isEnabled = false

        val nombreDos = findViewById<EditText>(R.id.editText_nombre_fruta_Editar)
        val idFruta = nombreDos.text.toString()
        val ind = buscarIndice(idFruta)

        val botonAceptarEditarF = findViewById<Button>(R.id.btn_aceptar_fruta_formulario_Editar)
        botonAceptarEditarF.setOnClickListener {
            arregloF[ind].codigoFruteria = findViewById<EditText>(R.id.editText_id_fruta_Editar).text.toString().toInt()
            arregloF[ind].nombreFruta = findViewById<EditText>(R.id.editText_nombre_fruta_Editar).text.toString()
            arregloF[ind].cantidad= findViewById<EditText>(R.id.editText_cantidad_fruta_Editar).text.toString().toInt()
            arregloF[ind].precioUnidad= findViewById<EditText>(R.id.editText_precio_unidad_fruta_Editar).text.toString().toDouble()
            arregloF[ind].esOrganica= findViewById<EditText>(R.id.editText_es_organico_fruta_Editar).text.toString().toBoolean()
            arregloF[ind].tipoFruta = findViewById<EditText>(R.id.editText_tipo_fruta_Editar).text.toString()

            finish()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun buscarIndice(nombreBuscado:String):Int{

        var idEncontrado: Int = -1

        for (frutas in arregloF) {
            if (frutas.nombreFruta == nombreBuscado) {
                idEncontrado = arregloF.indexOf(frutas)
                break
            }
        }
        return idEncontrado
    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this, clase);
        startActivity(intent);
    }
}