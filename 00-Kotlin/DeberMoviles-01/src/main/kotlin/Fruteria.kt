import java.io.Serializable

data class Fruteria(
    var nombre: String,
    var direccion: String,
    var cantidadEmpleados:Int,
    var estaAbierto: Boolean,
    var ingresosSemanales: Double
) : Serializable