package com.example.deber02_sqlite

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class FrutaListView : AppCompatActivity() {
    private lateinit var arreglo: MutableList<Frutas>
    private lateinit var listView: ListView
    private lateinit var adaptador: ArrayAdapter<Frutas>
    private var idItemSeleccionado = 0
    private lateinit var databaseHelper: BaseDeDatosSQLite
    private var fruteriaId = 0
    private var name=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruta_list_view)

        //Lista
        databaseHelper = BaseDeDatosSQLite(this)
        fruteriaId = intent.getIntExtra("fruteriaId", 0)
        name = intent.getStringExtra("nombre Fruteria") ?: ""
        arreglo = mutableListOf()

        adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1, // cómo se va a ver (XML)
            arreglo
        )
        listView = findViewById<ListView>(R.id.lv_fruteria)
        listView.adapter = adaptador

        // Cargar los datos desde la base de datos al iniciar
        cargarDatosDesdeBaseDeDatos()

        val etFrute = findViewById<TextView>(R.id.tv_frutas)
        etFrute.text = "Fruteria: $name"

        // Crear -> 'escuchar' al botón
        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_lv_fruta)
        botonAnadirListView.setOnClickListener {
            val intent = Intent(this, FrutaFormulario::class.java)
            intent.putExtra("fruteriaId", fruteriaId)
            startActivity(intent)
        }

        // Editar, Eliminar, Ver -> llamar al registro del menú
        registerForContextMenu(listView)
    }

    // Llamar a cargarDatosDesdeBaseDeDatos() después de agregar o eliminar un país
    override fun onResume() {
        super.onResume()
        cargarDatosDesdeBaseDeDatos()
    }

    private fun cargarDatosDesdeBaseDeDatos() {
        arreglo.clear()
        arreglo.addAll(databaseHelper.getFrutasByFruteria(fruteriaId))
       adaptador.notifyDataSetChanged()
    }

    //menu de contexto
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llenar opciones del menu
        val inflater=menuInflater
        inflater.inflate(R.menu.menufruta,menu)

        //obtener el id de ArrayListSeleccionado
        val info=menuInfo as AdapterView.AdapterContextMenuInfo
        val id=info.position
        idItemSeleccionado=id
    }

    //del item seleccionado
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar->{
                val fruta = arreglo[idItemSeleccionado]
                val intent = Intent(this, FrutaFormularioEditar::class.java)
                intent.putExtra(FrutaFormularioEditar.EXTRA_FRUTA, fruta)
                startActivity(intent)
                true
            }
            R.id.mi_eliminar->{
                abrirDialogo()
                return true
            }
            else-> super.onContextItemSelected(item)
        }
    }

    //accion al seleccionar Eliminar
    fun abrirDialogo(){
        val builder= AlertDialog.Builder(this)  //constructor de dialogo
        builder.setTitle("Desea eliminar? "+arreglo[idItemSeleccionado].nombreFruta)
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ dialog, which->
                val fruta = arreglo[idItemSeleccionado]
                databaseHelper.deleteFruta(fruta.codigoFruteria!!)
                arreglo.removeAt(idItemSeleccionado)
                adaptador.notifyDataSetChanged()
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo=builder.create()
        dialogo.show()
    }

    companion object {
       private const val EXTRA_FRUTA = "fruta"
    }
}