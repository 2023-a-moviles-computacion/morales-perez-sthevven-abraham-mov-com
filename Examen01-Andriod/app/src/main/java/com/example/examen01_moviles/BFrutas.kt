package com.example.examen01_moviles

class BFrutas(
    var id: Int?,
    var nombreFruta: String?,
    var cantidad: Int?,
    var precioUnidad: Double?,
    var esOrganica: String?,
    var tipoFruta: String?
) {
    override fun toString(): String {
        return    "ID: ${id}"+
                 "\nNombre de la Fruta: ${nombreFruta}" +
                "\nCantidad: ${cantidad}" +
                "\nPrecio por Unidad: ${precioUnidad}" +
                "\nEs Organica la Fruta: ${esOrganica}" +
                "\nTipo de Fruta: ${tipoFruta}"
    }

}