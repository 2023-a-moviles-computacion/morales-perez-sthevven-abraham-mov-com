package com.example.movilescomp2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.example.movilescomputacion2023a.ESqliteHelperEntrenador

class MainActivity : AppCompatActivity() {
     val callbackContenidoIntentExplicito=
         registerForActivityResult(
             ActivityResultContracts.StartActivityForResult()
         ){
             result ->
             if(result.resultCode == RESULT_OK){
                 if(result.data != null){
                     val data = result.data
                     "${data?.getStringExtra("nombreModificado")}"
                 }
             }
         }

    val callbackIntentPickUri=
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if(result.resultCode === RESULT_OK){
                if(result.data != null){
                    if(result.data!!.data !=null){
                        val uri: Uri =result.data!!.data!!
                        val cursor = contentResolver.query(uri,null,null,null,null,null)
                        cursor?.moveToFirst()
                        val indiceTelefono =cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono =cursor?.getString(indiceTelefono!!)
                        cursor?.close()
                        "Telefono ${telefono}"
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //base de satos sqlite
        EBaseDeDatos.tablaEntrenador= ESqliteHelperEntrenador(this)

        val botonCicloVida=findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener {
                irActividad(AACicloVida::class.java)
            }
        val botonListView =findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener {
                irActividad(BListView::class.java)
            }

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito
            .setOnClickListener{
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                callbackIntentPickUri.launch(intentConRespuesta)
            }
        val botonIntentExplicito = findViewById<Button>(R.id.btn_ir_intent_explicito)
        botonIntentExplicito
            .setOnClickListener{
                abrirActividadConParametros(CIntentExplicitoParametros::class.java)
            }

        val botonGoogleMaps = findViewById<Button>(R.id.btn_google_maps)
        botonGoogleMaps
            .setOnClickListener{
                irActividad(GGoogleMaps::class.java)
            }

        val botonFirebaseUI = findViewById<Button>(R.id.btn_intent_firebase_ui)
        botonFirebaseUI
            .setOnClickListener{
                irActividad(HFirebaseUIAuth::class.java)
            }

        val botonFirestore = findViewById<Button>(R.id.btn_intent_firestore)
        botonFirestore
            .setOnClickListener{
                irActividad(HFirebaseUIAuth::class.java)
            }
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        //envir [arametros solamente variables primitivos
        intentExplicito.putExtra("nombre","Sthevven")
        intentExplicito.putExtra("apellido","Morales")
        intentExplicito.putExtra("edad",23)

        callbackContenidoIntentExplicito.launch(intentExplicito)

    }

    fun irActividad(
    clase: Class<*>
    ){
       val intent = Intent(this,clase)
       startActivity(intent)
    }
}