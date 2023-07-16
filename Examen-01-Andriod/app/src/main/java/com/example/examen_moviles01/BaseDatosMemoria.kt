package com.example.examen_moviles01

    import android.os.Build
    import androidx.annotation.RequiresApi

    class BaseDatosMemoria {

        @RequiresApi(Build.VERSION_CODES.O)
        companion object {
            val listaFruterias: MutableList<Fruterias> = mutableListOf()
            val listaFrutas: MutableList<Frutas> = mutableListOf()


            init {

                agregarFruterias(Fruterias(
                    1,
                    "Conchita S.A",
                    "Alangasi Barrio Angamarca",
                    3,
                    true,
                    650.25)
                )
               // se asocian a la fruteria conchita
                agregarFrutas(
                    Frutas(
                        1,
                        "Manzana",
                        100,
                        0.25,
                        true,
                        "Dulce")
                )

                agregarFrutas(
                    Frutas(
                        1,
                        "Limon",
                        50,
                        0.15,
                       false,
                        "Acida")
                )

            }

            fun agregarFruterias(fruteria: Fruterias) {
                listaFruterias.add(fruteria)
            }


            fun agregarFrutas(fruta: Frutas) {
                listaFrutas.add(fruta)
            }

        }
    }
