package com.example.examen01_moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat.startActivity

class BListViewFruteria : AppCompatActivity() {
    private lateinit var  arreglo: ArrayAdapter<BFruteria>
    private lateinit var  fruterias: ArrayList<BFruteria>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view_fruteria)

        //Obtener las consolas desde la base de datos
       fruterias = obtenerFruteriasDesdeLaBaseDeDatos()



        val listView = findViewById<ListView>(R.id.lv_fruterias)
        arreglo = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1,
            fruterias
        )


        listView.adapter = arreglo
        arreglo.notifyDataSetChanged()

        registerForContextMenu(listView)


        val botonListView = findViewById<Button>(R.id.btn_ir_crear_fruteria)
        botonListView
            .setOnClickListener{
                irActividad(ECrudFruteria::class.java)
            }

    }

    override fun onResume() {
        super.onResume()

        // Obtén las fruterias actualizadas desde la base de datos
        val fruteriasActualizadas = obtenerFruteriasDesdeLaBaseDeDatos()

        // Borra los elementos del adaptador actual
        arreglo.clear()

        // Agrega las consolas actualizadas al adaptador
        arreglo.addAll(fruteriasActualizadas)

        // Notifica al adaptador que los datos han cambiado
        arreglo.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val posicionSeleccionada = info.position
        val fruteriaSeleccionada = fruterias[posicionSeleccionada]
        val idSeleccionado = fruteriaSeleccionada.id

        return when (item.itemId){
            R.id.op_editar ->{

                return true
            }
            R.id.op_eliminar ->{
                if(eliminarFruteria(idSeleccionado)){
                    Toast.makeText(this, "Fruteria eliminada", Toast.LENGTH_SHORT).show()
                   fruterias.removeAt(posicionSeleccionada)
                    arreglo.notifyDataSetChanged()
                } else
                {
                    Toast.makeText(this, "Error al eliminar la fruteria", Toast.LENGTH_SHORT).show()
                }

                return true
            }
            R.id.op_ver_frutas ->{

                return true
            }

            else -> super.onContextItemSelected(item)
        }

    }

    private fun obtenerFruteriasDesdeLaBaseDeDatos(): ArrayList<BFruteria> {
        val dbHelper = ESqliteHelperFruteria(this)
        val fruterias = dbHelper.obtenerTodasLasFruterias()
        dbHelper.close()
        return fruterias
    }

    private fun eliminarFruteria(id: Int): Boolean {
        val dbHelper = ESqliteHelperFruteria(this)
        val conf = dbHelper.eliminarFruteriaFormulario(id)
        dbHelper.close()
        return conf
    }




    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}