package com.example.examen_moviles01

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi

class FruteriasFormulario: AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    var arrelo = BaseDatosMemoria.listaFruterias

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruterias_formulario)

        val botonAceptarCreacion = findViewById<Button>(R.id.btn_aceptar_fruteria_formulario)
        botonAceptarCreacion.setOnClickListener {
            var id = findViewById<EditText>(R.id.editText_id_fruteria).text.toString().toInt()
            var nombre = findViewById<EditText>(R.id.editText_nombre_fruteria).text.toString()
            var direccion = findViewById<EditText>(R.id.editText_direccion_fruteria).text.toString()
            var cantidadEmpleados = findViewById<EditText>(R.id.editText_cantidad_empleados_fruteria).text.toString().toInt()
            var estaAbierto = findViewById<EditText>(R.id.editText_esta_abierto_fruteria).text.toString().toBoolean()
            var ingresosSemanales = findViewById<EditText>(R.id.editText_ingresos_semanales_fruteria).text.toString().toDouble()

            arrelo.add(Fruterias(id,nombre,direccion,cantidadEmpleados,estaAbierto,ingresosSemanales))

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