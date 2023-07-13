import java.io.*

class FrutasCRUD {
    private val archivo = File("frutas.txt")

    fun crearFrutas(fruta: Frutas) {
        val frutas = cargarFrutas()
        frutas.add(fruta)
        guardarFrutas(frutas)
        println("Fruta creada con éxito.")
    }

    fun listarFrutas() {
        val frutas = cargarFrutas()
        if (frutas.isNotEmpty()) {
            for ((index, fruta) in frutas.withIndex()) {
                println("=== Fruta ${index + 1} ===")
                println(fruta)
            }
        } else {
            println("No hay frutas registradas.")
        }
    }

    fun actualizarFrutas(index: Int, nuevaFruta: Frutas) {
        val frutas = cargarFrutas()
        if (index >= 0 && index < frutas.size) {
            frutas[index] = nuevaFruta
            guardarFrutas(frutas)
            println("Fruta actualizada con éxito.")
        }
    }

    fun eliminarFrutas(index: Int) {
        val frutas = cargarFrutas()
        if (index >= 0 && index < frutas.size) {
            frutas.removeAt(index)
            guardarFrutas(frutas)
            println("Fruta eliminada con éxito.")
        } else {
            println("Código de la fruta es inválido.")
        }
    }

    fun cargarFrutas(): MutableList<Frutas> {
        val frutas: MutableList<Frutas> = mutableListOf()
        if (archivo.exists()) {
            archivo.bufferedReader(Charsets.UTF_8).use { reader ->
                reader.lineSequence().forEach { line ->
                    val campos = line.split(",")
                    if (campos.size >= 4) {
                        val nombreFruta = campos[0]
                        val cantidad = campos[1].toInt()
                        val precioUnidad = campos[2].toDouble()
                        val esOrganica = campos[3].toBoolean()
                        val tipoFruta = campos[4]
                        val fruta = Frutas(nombreFruta, cantidad, precioUnidad, esOrganica, tipoFruta)
                        frutas.add(fruta)
                    }
                }
            }
        }
        return frutas
    }

    private fun guardarFrutas(frutas: List<Frutas>) {
        archivo.bufferedWriter(Charsets.UTF_8).use { writer ->
            for (fruta in frutas) {
                writer.write("${fruta.nombreFruta},${fruta.cantidad},${fruta.precioUnidad},${fruta.esOrganica},${fruta.tipoFruta}")
                writer.newLine()
            }
        }
    }
}
