package com.example.examen_moviles01

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog

class FrutasListView : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    var arreglo = BaseDatosMemoria.listaFrutas
    var idItemSeleccionado = 0

    var adaptador: ArrayAdapter<Frutas>? = null

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frutas_list_view)

        val fruteriaSeleccionada = intent.getSerializableExtra("Fruteria") as Fruterias
        arreglo = BaseDatosMemoria.listaFrutas.filter { it.codigoFruteria == fruteriaSeleccionada.id }.toMutableList()

        val namePadre = findViewById<TextView>(R.id.tv_nombre_padre)
        namePadre.setText(fruteriaSeleccionada.nombre)

        val listView = findViewById<ListView>(R.id.listView_frutas)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador!!.notifyDataSetChanged()

        val botonCrearFrutasListView = findViewById<Button>(R.id.btn_crear_frutas)
        botonCrearFrutasListView
            .setOnClickListener {
                val idFruteriaPadre = fruteriaSeleccionada.id
                val intent =Intent(this, FrutasFormulario::class.java)
                intent.putExtra("idFruteria",idFruteriaPadre)
                Log.i("TAG", "Vista de la lista padre ${idFruteriaPadre}")
                startActivity(intent)
            }
        registerForContextMenu(listView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        val fruteriaSeleccionada = intent.getSerializableExtra("Fruteria") as Fruterias
        arreglo = BaseDatosMemoria.listaFrutas.filter { it.codigoFruteria == fruteriaSeleccionada.id }.toMutableList()
        adaptador?.clear()
        adaptador?.addAll(arreglo)
        adaptador?.notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun eliminarFruta(){
        val frutaEliminado = arreglo[idItemSeleccionado]
        BaseDatosMemoria.listaFrutas.remove(frutaEliminado)
        arreglo.removeAt(idItemSeleccionado)
        actualizarLista(adaptador)
        adaptador?.clear()
        adaptador?.addAll(arreglo)
        adaptador?.notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editarFruta(){
        val frutaSeleccionado = arreglo[idItemSeleccionado]
        val intent = Intent(this, FrutasFormularioEditar::class.java)
        intent.putExtra("Fruta", frutaSeleccionado)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun actualizarLista(
        adaptador: ArrayAdapter<Frutas>?
    ){
        if (adaptador != null) {
            adaptador.notifyDataSetChanged()
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menufrutas, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar ->{
                //"${idItemSeleccionado}"
                editarFruta()
                return true
            }
            R.id.mi_eliminar->{
                //"${idItemSeleccionado}"
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this, clase);
        startActivity(intent);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar la fruta")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog, which -> eliminarFruta()
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val  dialogo = builder.create()
        dialogo.show()
    }

}