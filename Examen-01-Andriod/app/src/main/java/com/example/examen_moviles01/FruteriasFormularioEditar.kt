package com.example.examen_moviles01

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi

class FruteriasFormularioEditar : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    var arreglo = BaseDatosMemoria.listaFruterias


    @SuppressLint("CutPasteId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruterias_formulario_editar)


        val fruteriaSeleccionada = intent.getSerializableExtra("fruteria") as Fruterias

        val id = findViewById<EditText>(R.id.editText_id_fruteria_editar)
        val nombre = findViewById<EditText>(R.id.editText_nombre_fruteria_editar)
        val direccion = findViewById<EditText>(R.id.editText_direccion_fruteria_editar)
        val cantidadEmpleados = findViewById<EditText>(R.id.editText_cantidad_empleados_fruteria_editar)
        val estaAbierto = findViewById<EditText>(R.id.editText_esta_abierto_fruteria_editar)
        val ingresosSemanales = findViewById<EditText>(R.id.editText_ingresos_semanales_fruteria_editar)



        id.setText(fruteriaSeleccionada.id.toString().toInt())
        nombre.setText(fruteriaSeleccionada.nombre.toString())
        direccion.setText(fruteriaSeleccionada.direccion.toString())
        cantidadEmpleados.setText(fruteriaSeleccionada.cantidadEmpleados.toString())
        estaAbierto.setText(fruteriaSeleccionada.estaAbierto.toString())
        ingresosSemanales.setText(fruteriaSeleccionada.ingresosSemanales.toString())



        val idDos = findViewById<EditText>(R.id.editText_id_fruteria_editar)
        val idFruteria = idDos.text.toString().toInt()
        val ind = buscarIndice(idFruteria)

        id.isEnabled = false

        val botonAceptarEditar = findViewById<Button>(R.id.btn_aceptar_fruteria_formulario_editar)
        botonAceptarEditar.setOnClickListener {
            arreglo[ind].id = findViewById<EditText>(R.id.editText_id_fruteria_editar).text.toString().toInt()
            arreglo[ind].nombre = findViewById<EditText>(R.id.editText_nombre_fruteria_editar).text.toString()
            arreglo[ind].direccion = findViewById<EditText>(R.id.editText_direccion_fruteria_editar).text.toString()
            arreglo[ind].cantidadEmpleados = findViewById<EditText>(R.id.editText_cantidad_empleados_fruteria_editar).text.toString().toInt()
            arreglo[ind].estaAbierto = findViewById<EditText>(R.id.editText_esta_abierto_fruteria_editar).text.toString().toBoolean()
            arreglo[ind].ingresosSemanales = findViewById<EditText>(R.id.editText_ingresos_semanales_fruteria_editar).text.toString().toDouble()
            //irActividad(MainActivity::class.java)
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun buscarIndice(idBuscado:Int):Int{

        var idEncontrado: Int = -1

        for (fruteria in arreglo) {
            if (fruteria.id == idBuscado) {
                idEncontrado = arreglo.indexOf(fruteria)
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