package com.example.examen01_moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListViewFrutas : AppCompatActivity() {

    private lateinit var  arreglo: ArrayAdapter<BFrutas>
    private lateinit var  frutas: ArrayList<BFrutas>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view_frutas)

        // Obtener la lista de videojuegos relacionados a la consola seleccionada
        frutas = obtenerFrutasPorId()

        // Configurar el adaptador para la lista de videojuegos
        val listView = findViewById<ListView>(R.id.lv_frutas)
        arreglo = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1,
            frutas
        )

        listView.adapter = arreglo
        arreglo.notifyDataSetChanged()

        registerForContextMenu(listView)
    }

    private fun obtenerFrutasPorId(): ArrayList<BFrutas> {
        val dbHelper = ESqliteHelperFrutas(this)
        val fruteriaId = intent.getIntExtra("fruteriaId", 1)
        val frutas = dbHelper.obtenerFrutasDeFruterias(fruteriaId)
        dbHelper.close()
        return frutas
    }

    fun irActividad(
        clase: Class<*>,
        fruteriaID: Int
    ){
        val intent = Intent(this, clase)
        intent.putExtra("FRUTERIA_ID",fruteriaID)
        startActivity(intent)
    }
}