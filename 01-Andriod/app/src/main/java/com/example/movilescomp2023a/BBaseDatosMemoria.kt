package com.example.movilescomp2023a

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init{
            arregloBEntrenador
                .add(
                    BEntrenador(1,"Sthevven","s@s.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2,"Fernando","f@f.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3,"Gonzalo","g@g.com")
                )
        }
    }
}