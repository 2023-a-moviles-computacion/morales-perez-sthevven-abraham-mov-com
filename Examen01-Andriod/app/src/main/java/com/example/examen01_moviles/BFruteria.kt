package com.example.examen01_moviles

class BFruteria(
    var id: Int?,
    var nombre:String?,
    var direccion:String?,
    var cantidadEmpleados: Int?,
    var estaAbierto: String?,
    var ingresosSemanales: Double?,
) {

    override fun toString(): String {
        return   "ID: ${id}"+
                 "\nNombre: ${nombre}" +
                "\nDireccion: ${direccion}" +
                "\nCantidad Empleados: ${cantidadEmpleados}" +
                "\nEsta Abierto?: ${estaAbierto}" +
                "\nIngresos semanales: ${ingresosSemanales}"
    }

}