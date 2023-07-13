
import java.io.Serializable
import java.util.*

data class Frutas(
    var nombreFruta: String,
    var cantidad: Int,
    var precioUnidad: Double,
    var esOrganica: Boolean,
    var tipoFruta: String
) : Serializable
