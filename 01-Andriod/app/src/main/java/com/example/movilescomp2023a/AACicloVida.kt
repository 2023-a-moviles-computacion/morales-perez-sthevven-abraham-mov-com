package com.example.movilescomp2023a

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.movilescomp2023a.databinding.ActivityAacicloVidaBinding

class AACicloVida : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAacicloVidaBinding
    var textoGlobal= ""
    fun mostrarSnack(texto:String){
        textoGlobal += texto
        Snackbar.make(findViewById(R.id.btn_ciclo_vida),
            textoGlobal, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAacicloVidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_aaciclo_vida3)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        mostrarSnack("OnCreate")
    }// oncreate fin de bloque de codigo
    override fun onStart(){
        super.onStart()
        mostrarSnack("onStart")
    }
    override fun onResume(){
        super.onResume()
        mostrarSnack("onResume")
    }
    override fun onRestart(){
        super.onRestart()
        mostrarSnack("onRestart")
    }
    override fun onPause(){
        super.onPause()
        mostrarSnack("onPause")
    }
    override fun onStop(){
        super.onStop()
        mostrarSnack("onStop")
    }
    override fun onDestroy(){
        super.onDestroy()
        mostrarSnack("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run{
            //guardar las variables
            //primitivos
            putString("textGuardado",textoGlobal)
            //putInt("numeroGuardado",numero)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //recuperar las variables
        //primitivos
        val textoRecuperado:String?=savedInstanceState.getString("textGuardado")
        if(textoRecuperado!=null){
            mostrarSnack(textoRecuperado)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_aaciclo_vida3)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}