import java.io.*

class FruteriaCRUD {
    private val archivo = File("fruterias.txt")

    fun crearFruteria(fruteria: Fruteria) {
        val fruterias = cargarFruterias()
        fruterias.add(fruteria)
        guardarFruterias(fruterias)
        println("Fruteria creada con éxito.")
    }

    fun listarFruterias() {
        val fruterias = cargarFruterias()
        if (fruterias.isNotEmpty()) {
            for ((index, fruteria) in fruterias.withIndex()) {
                println("=== Fruteria ${index + 1} ===")
                println(fruteria)
            }
        } else {
            println("No hay fruterias registradas.")
        }
    }

    fun actualizarFruteria(index: Int, nuevaFruteria: Fruteria) {
        val fruterias = cargarFruterias()
        if (index >= 0 && index < fruterias.size) {
            fruterias[index] = nuevaFruteria
            guardarFruterias(fruterias)
            println("Fruteria actualizada con éxito.")
        }
    }

    fun eliminarFruteria(index: Int) {
        val fruterias = cargarFruterias()
        if (index >= 0 && index < fruterias.size) {
            fruterias.removeAt(index)
            guardarFruterias(fruterias)
            println("Fruteria eliminada con éxito.")
        } else {
            println("Código de la Futeria es inválido.")
        }
    }

    fun cargarFruterias(): MutableList<Fruteria> {
        val fruterias: MutableList<Fruteria> = mutableListOf()
        if (archivo.exists()) {
            archivo.bufferedReader(Charsets.UTF_8).use { reader ->
                reader.lineSequence().forEach { line ->
                    val campos = line.split(",")
                    if (campos.size >= 4) {
                        val nombre = campos[0]
                        val direccion = campos[1]
                        //val telefono = campos[2]
                        val cantidadEmpleados = campos[2].toInt()
                        val estaAbierto = campos[3].toBoolean()
                        val ingresosSemanales = campos[4].toDouble()
                        val fruteria = Fruteria(nombre, direccion, cantidadEmpleados, estaAbierto, ingresosSemanales)
                        fruterias.add(fruteria)
                    }
                }
            }
        }
        return fruterias
    }

    private fun guardarFruterias(fruterias: List<Fruteria>) {
        archivo.bufferedWriter(Charsets.UTF_8).use { writer ->
            for (fruteria in fruterias) {
                writer.write("${fruteria.nombre},${fruteria.direccion},${fruteria.cantidadEmpleados},${fruteria.estaAbierto},${fruteria.ingresosSemanales}")
                writer.newLine()
            }
        }
    }
}



