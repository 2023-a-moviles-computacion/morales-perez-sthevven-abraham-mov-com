package com.example.examen01_moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Base de datos sqlite
        EBaseDeDatos.coBDatos = ESqliteHelperFruteria(this)
        EBaseDeDatos.coBDatos2 = ESqliteHelperFrutas(this)

        val btnListView = findViewById<Button>(R.id.btn_abrir)
        btnListView.setOnClickListener { irActividad(BListViewFruteria::class.java) }


    }



    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}