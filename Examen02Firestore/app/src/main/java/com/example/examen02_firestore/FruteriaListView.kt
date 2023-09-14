package com.example.examen02_firestore
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.ArrayList

class FruteriaListView : AppCompatActivity() {

    private var idItemSeleccionado = 0
    val arreglo:ArrayList<Fruterias> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruteria_list_view)

        val listView=findViewById<ListView>(R.id.lv_fruteria)
        val adaptador=ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //como se va a ver (XML)
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()

        //Crear -> 'escuchar' al boton
        val botonAnadirListView=findViewById<Button>(
            R.id.btn_anadir_lv_fruteria)

        botonAnadirListView.setOnClickListener{
            val intent = Intent(this, FruteriaFormulario::class.java)
            startActivity(intent)
        }

        //Editar, Eliminar, Ver -> llamar al registro menu
        registerForContextMenu(listView)

        cargarDatosDesdeFirestore(adaptador)

        adaptador.notifyDataSetChanged() // Actualiza la ListView

    }

    fun cargarDatosDesdeFirestore(adaptador: ArrayAdapter<Fruterias>){
        val db=Firebase.firestore
        val cRefUnico=db.collection("fruterias")
        arreglo.clear() //cada consulta se limpia el arreglo
        adaptador.notifyDataSetChanged()
        cRefUnico
            .get()
            .addOnSuccessListener { //it (esto es lo q sea q llegue)
                for(fru in it){
                    fru.id //id quemado o autogenerado
                    anadirAArregloCont(fru)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener{
                //Errores
            }
    }

    fun anadirAArregloCont(cont:QueryDocumentSnapshot){
        val nuevo=Fruterias(
            cont.data.get("id") as String?,
            cont.data.get("nombre") as String?,
            cont.data.get("direccion") as String?,
            cont.data.get("numeroEmpleados") as Long?,
            cont.data.get("estaAbierto") as Boolean?,
            cont.data.get("ingresosSemanales") as? Double
        )
        arreglo.add(nuevo)
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
        inflater.inflate(R.menu.menufruteria,menu)

        //obtener el id de ArrayListSeleccionado
        val info=menuInfo as AdapterView.AdapterContextMenuInfo
        val id=info.position
        idItemSeleccionado=id
    }

    //del item seleccionado
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar->{
                val fruteria = arreglo[idItemSeleccionado]
                val intent = Intent(this, FruteriaFormularioEditar::class.java)
                intent.putExtra(FruteriaFormularioEditar.EXTRA_FRUTERIA, fruteria)
                startActivity(intent)
                true
            }
            R.id.mi_eliminar->{
                abrirDialogo()
                true
            }
            R.id.mi_ver->{

                val intent = Intent(this, FrutaListView::class.java)
                intent.putExtra("fruteriaId", arreglo[idItemSeleccionado].id)
                startActivity(intent)
                //adapte
                true
            }
            else-> super.onContextItemSelected(item)
        }
    }

    //accion al seleccionar Eliminar
    fun abrirDialogo(){
        val builder= AlertDialog.Builder(this)  //constructor de dialogo
        builder.setTitle("Desea eliminar? " +arreglo[idItemSeleccionado].nombre)
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ dialog, which->
                val fruteriaId = arreglo[idItemSeleccionado].id
                if (fruteriaId != null) {
                    eliminarRegistro(fruteriaId)
                }
                //adaptador.notifyDataSetChanged()
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo=builder.create()
        dialogo.show()
    }

    fun eliminarRegistro(id:String){
        val db=Firebase.firestore
        val ref=db.collection("fruterias")
        ref .document(id)
            .delete()
            .addOnCompleteListener{task ->
                if (task.isSuccessful) {
                    // Eliminación en Firestore exitosa,
                    arreglo.removeAt(idItemSeleccionado)

                } else {
                    // Si la eliminación en Firestore falla, puedes manejar el error aquí
                }
            }
            .addOnFailureListener {
                // Si hay un error al eliminar en Firestore, puedes manejarlo aquí
            }
    }

    companion object { //instancias estàticas
        const val EXTRA_FRUTERIA = "fruteria" //Esta constante se utiliza como clave para pasar datos extras a través de un Intent al iniciar una actividad.
    }
}


