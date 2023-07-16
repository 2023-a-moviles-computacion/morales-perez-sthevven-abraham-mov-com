package com.example.examen01_moviles

class BBaseDatosMemoria {

    companion object{
        val arregloBFruteria = arrayListOf<BFruteria>()
        init{
            arregloBFruteria
                .add(

                    BFruteria("Conchita", "EPN", 2,"true", 600.98)
                )
            arregloBFruteria
                .add(

                    BFruteria("La Vecina", "Alangasi",3, "true",900.52)
                )

        }
    }
}