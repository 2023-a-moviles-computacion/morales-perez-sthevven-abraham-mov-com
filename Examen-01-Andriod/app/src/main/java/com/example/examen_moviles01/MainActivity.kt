package com.example.examen_moviles01

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    val arreglo = BaseDatosMemoria.listaFruterias
    var idItemSeleccionado = 0

    var adaptador: ArrayAdapter<Fruterias>? = null

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.list_view_fruteria)

        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )

        listView.adapter = adaptador
        adaptador!!.notifyDataSetChanged()

        val botonCrearFruteriaListView = findViewById<Button>(R.id.btn_crear_fruteria)
        botonCrearFruteriaListView
            .setOnClickListener {
                irActividad(FruteriasFormulario::class.java)
            }
        registerForContextMenu(listView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        actualizarLista(adaptador!!)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun eliminarFruteria(){

        val iterador = BaseDatosMemoria.listaFrutas.iterator()
        while (iterador.hasNext()) {
            val fruta = iterador.next()
            if (fruta.codigoFruteria == this.arreglo[idItemSeleccionado].id) {
                iterador.remove() // Eliminar de forma segura utilizando el iterador
            }
        }
        arreglo.removeAt(idItemSeleccionado)
        actualizarLista(adaptador)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editarFruteria(){
        val fruteriaSeleccionada = arreglo[idItemSeleccionado]
        val intent = Intent(this, FruteriasFormularioEditar :: class.java)
        intent.putExtra("Fruteria", fruteriaSeleccionada)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun verFrutasFruteria(){
        val fruteriaSeleccionada = arreglo[idItemSeleccionado]
        val intent = Intent(this, FrutasListView :: class.java)
        intent.putExtra("Fruteria",fruteriaSeleccionada)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun actualizarLista(
        adaptador: ArrayAdapter<Fruterias>?
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
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar ->{
                //"${idItemSeleccionado}"
                //irActividad(FruteriasFormularioEditar::class.java)
                editarFruteria()
                return true
            }
            R.id.mi_eliminar->{
                //"${idItemSeleccionado}"
                abrirDialogo()
                return true
            }
            R.id.mi_ver_fruteria->{
                //"${idItemSeleccionado}"
                verFrutasFruteria()
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
        builder.setTitle("Desea eliminar la fruteria")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog, which -> eliminarFruteria()
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