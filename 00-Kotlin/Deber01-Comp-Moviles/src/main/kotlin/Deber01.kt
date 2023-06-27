import java.io.File
import java.util.*

data class Fruta(
    val nombre: String,
    val cantidad: Int,
    val precio: Double,
    val fechaCaducidad: String,
    val esOrganica: Boolean
)

data class Fruteria(
    val nombre: String,
    val direccion: String,
    val telefono: Int,
    val horario: String,
    val frutas: ArrayList<Fruta> = ArrayList()
)

fun guardarFruta(fruta: Fruta) {
    val archivoFruta = File("${fruta.nombre}.txt")
    val contenido = StringBuilder()

    contenido.appendLine("Nombre: ${fruta.nombre}")
    contenido.appendLine("Cantidad: ${fruta.cantidad}")
    contenido.appendLine("Precio: ${fruta.precio}")
    contenido.appendLine("Fecha de caducidad: ${fruta.fechaCaducidad}")
    contenido.appendLine("Es orgánica: ${fruta.esOrganica}")

    archivoFruta.writeText(contenido.toString())
    println("La fruta se ha guardado en el archivo: ${archivoFruta.absolutePath}")
}

fun guardarFruteria(fruteria: Fruteria) {
    val archivoFruteria = File("${fruteria.nombre}.txt")
    val contenido = StringBuilder()

    contenido.appendLine("Nombre: ${fruteria.nombre}")
    contenido.appendLine("Dirección: ${fruteria.direccion}")
    contenido.appendLine("Teléfono: ${fruteria.telefono}")
    contenido.appendLine("Horario: ${fruteria.horario}")

    contenido.appendLine("Frutas:")
    for (fruta in fruteria.frutas) {
        contenido.appendLine("Nombre: ${fruta.nombre}")
        contenido.appendLine("Cantidad: ${fruta.cantidad}")
        contenido.appendLine("Precio: ${fruta.precio}")
        contenido.appendLine("Fecha de caducidad: ${fruta.fechaCaducidad}")
        contenido.appendLine("Es orgánica: ${fruta.esOrganica}")
        contenido.appendLine()
    }

    archivoFruteria.writeText(contenido.toString())
    println("La frutería se ha guardado en el archivo: ${archivoFruteria.absolutePath}")
}

fun crearFruta(scanner: Scanner): Fruta {
    println("Ingrese el nombre de la fruta:")
    val nombre = scanner.nextLine()

    println("Ingrese la cantidad de la fruta:")
    val cantidad = scanner.nextInt()

    println("Ingrese el precio de la fruta:")
    val precio = scanner.nextDouble()

    println("Ingrese la fecha de caducidad de la fruta:")
    val fechaCaducidad = scanner.next()

    println("Es orgánica (true/false):")
    val esOrganica = scanner.nextBoolean()

    return Fruta(nombre, cantidad, precio, fechaCaducidad, esOrganica)
}

fun crearFruteria(scanner: Scanner): Fruteria {
    println("Ingrese el nombre de la frutería:")
    val nombre = scanner.nextLine()

    println("Ingrese la dirección de la frutería:")
    val direccion = scanner.nextLine()

    println("Ingrese el teléfono de la frutería:")
    val telefono = scanner.nextLine()

    println("Ingrese el horario de la frutería:")
    val horario = scanner.nextLine()

    val frutas = ArrayList<Fruta>()
    println("Ingrese las frutas disponibles en la frutería (Ingrese 'salir' para finalizar):")
    while (true) {
       // val fruta
        val frutaNombre = scanner.nextLine()
        if (frutaNombre == "salir") {
            break
        }

        val fruta = crearFruta(scanner)
        frutas.add(fruta)

        println("La fruta ha sido agregada a la frutería.")
        println()
    }

    return Fruteria(nombre, direccion, telefono, horario, frutas)
}

fun main() {
    val scanner = Scanner(System.`in`)

    while (true) {
        println("¿Qué entidad deseas manipular?")
        println("1. Frutería")
        println("2. Fruta")
        println("3. Salir")

        when (scanner.nextInt()) {
            1 -> {
                println("¿Qué acción deseas realizar con la frutería?")
                println("1. Crear una frutería")
                println("2. Mostrar una frutería")
                println("3. Actualizar una frutería")
                println("4. Eliminar una frutería")

                when (scanner.nextInt()) {
                    1 -> {
                        val fruteria = crearFruteria(scanner)
                        guardarFruteria(fruteria)
                        println("La frutería ha sido creada exitosamente.")
                        println()
                    }
                    2 -> {
                        println("Ingrese el nombre de la frutería a mostrar:")
                        val nombreFruteria = scanner.next()

                        val archivoFruteria = File("$nombreFruteria.txt")
                        if (archivoFruteria.exists()) {
                            println("Frutería:")
                            println(archivoFruteria.readText())
                        } else {
                            println("No se encontró la frutería.")
                        }
                        println()
                    }
                    3 -> {
                        println("Ingrese el nombre de la frutería a actualizar:")
                        val nombreFruteria = scanner.next()

                        val archivoFruteria = File("$nombreFruteria.txt")
                        if (archivoFruteria.exists()) {
                            val fruteria = crearFruteria(scanner)
                            guardarFruteria(fruteria)
                            println("La frutería ha sido actualizada exitosamente.")
                        } else {
                            println("No se encontró la frutería.")
                        }
                        println()
                    }
                    4 -> {
                        println("Ingrese el nombre de la frutería a eliminar:")
                        val nombreFruteria = scanner.next()

                        val archivoFruteria = File("$nombreFruteria.txt")
                        if (archivoFruteria.exists()) {
                            archivoFruteria.delete()
                            println("La frutería ha sido eliminada exitosamente.")
                        } else {
                            println("No se encontró la frutería.")
                        }
                        println()
                    }
                    else -> {
                        println("Opción inválida. Por favor, ingrese un número válido.")
                        println()
                    }
                }
            }
            2 -> {
                println("¿Qué acción deseas realizar con la fruta?")
                println("1. Crear una fruta")
                println("2. Mostrar una fruta")
                println("3. Actualizar una fruta")
                println("4. Eliminar una fruta")

                when (scanner.nextInt()) {
                    1 -> {
                        val fruta = crearFruta(scanner)
                        guardarFruta(fruta)
                        println("La fruta ha sido creada exitosamente.")
                        println()
                    }
                    2 -> {
                        println("Ingrese el nombre de la fruta a mostrar:")
                        val nombreFruta = scanner.next()

                        val archivoFruta = File("$nombreFruta.txt")
                        if (archivoFruta.exists()) {
                            println("Fruta:")
                            println(archivoFruta.readText())
                        } else {
                            println("No se encontró la fruta.")
                        }
                        println()
                    }
                    3 -> {
                        println("Ingrese el nombre de la fruta a actualizar:")
                        val nombreFruta = scanner.next()

                        val archivoFruta = File("$nombreFruta.txt")
                        if (archivoFruta.exists()) {
                            val fruta = crearFruta(scanner)
                            guardarFruta(fruta)
                            println("La fruta ha sido actualizada exitosamente.")
                        } else {
                            println("No se encontró la fruta.")
                        }
                        println()
                    }
                    4 -> {
                        println("Ingrese el nombre de la fruta a eliminar:")
                        val nombreFruta = scanner.next()

                        val archivoFruta = File("$nombreFruta.txt")
                        if (archivoFruta.exists()) {
                            archivoFruta.delete()
                            println("La fruta ha sido eliminada exitosamente.")
                        } else {
                            println("No se encontró la fruta.")
                        }
                        println()
                    }
                    else -> {
                        println("Opción inválida. Por favor, ingrese un número válido.")
                        println()
                    }
                }
            }
            3 -> {
                println("¡Gracias por usar el programa!")
                return
            }
            else -> {
                println("Opción inválida. Por favor, ingrese un número válido.")
                println()
            }
        }
    }
}

