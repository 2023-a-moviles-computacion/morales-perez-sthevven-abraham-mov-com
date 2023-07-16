package com.example.examen_moviles01

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable

@RequiresApi(Build.VERSION_CODES.O)
class Fruterias (
    var id: Int,
    var nombre: String?,
    var direccion: String?,
    var cantidadEmpleados: Int?,
    var estaAbierto: Boolean?,
    var ingresosSemanales: Double?
    //creamos un constructor para nuestra clase
): Serializable {
    constructor(): this(
        0,
        null,
        null,
        null,
        null,
        null
    )

    override fun toString(): String {
        return "► Nombre Fruteria: ${nombre}\n\t Direccion: ${direccion}\n\t Cantidad Empleados: ${cantidadEmpleados}\n\t Esta Abierto? ${estaAbierto}\n\t  Ingresos Semanales: ${ingresosSemanales}"
    }

}