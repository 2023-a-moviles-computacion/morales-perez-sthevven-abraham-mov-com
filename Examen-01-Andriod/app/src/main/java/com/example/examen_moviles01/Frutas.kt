package com.example.examen_moviles01

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable

@RequiresApi(Build.VERSION_CODES.O)
class Frutas(
    var codigoFruteria :Int?,
    var nombreFruta: String?,
    var cantidad: Int?,
    var precioUnidad: Double?,
    var esOrganica: Boolean?,
    var tipoFruta: String?
):Serializable {

    override fun toString(): String {
        return "►${nombreFruta}\n\tCantidad: ${cantidad}\n\tPrecio Unidad: ${precioUnidad}\n\tEs Organica?: ${esOrganica}\n\tTipo de Fruta: ${tipoFruta}"
    }
}