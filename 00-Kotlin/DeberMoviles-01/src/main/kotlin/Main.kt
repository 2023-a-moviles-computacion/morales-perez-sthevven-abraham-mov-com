import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val fruteriaCRUD = FruteriaCRUD()
    val frutasCRUD = FrutasCRUD()

    val formatoFecha = SimpleDateFormat("dd/MM/yyyy")

    loop@ while (true) {
        println("==== MENÚ ====")
        println("1. Crear Fruteria")
        println("2. Crear Frutas")
        println("3. Listar Fruterias")
        println("4. Listar Frutas")
        println("5. Actualizar Fruteria")
        println("6. Actualizar Fruta")
        println("7. Eliminar Fruteria")
        println("8. Eliminar Fruta")
        println("9. Salir")
        println("Ingrese la opción deseada:")

        when (scanner.nextInt()) {
            1 -> {
                scanner.nextLine()
                println("Ingrese el nombre de la Fruteria:")
                val nombre = scanner.nextLine()
                println("Ingrese la direccion de la Fruteria:")
                val direccion = scanner.nextLine()
                println("Ingrese la cantidad de empleados de la Fruteria:")
                val cantidadEmpleados = scanner.nextInt()
                scanner.nextLine() // Consumir el salto de línea pendiente
                println("La Fruteria está abierta? (true/false):")
                val estaAbierto = scanner.nextBoolean()
                scanner.nextLine() // Consumir el salto de línea pendiente
                println("Ingrese los ingresos semanales de la Fruteria:")
                val ingresosSemanales = scanner.nextDouble()
                scanner.nextLine() // Consumir el salto de línea pendiente

                val fruteria = Fruteria(nombre, direccion, cantidadEmpleados, estaAbierto, ingresosSemanales)
                fruteriaCRUD.crearFruteria(fruteria)
            }
            2 -> {
                scanner.nextLine()
                println("Ingrese el nombre de la fruta:")
                val nombreFruta = scanner.nextLine()
                println("Ingrese la cantidad de fruta:")
                val cantidad = scanner.nextInt()
                println("Ingrese el precio por unidad de la fruta:")
                val precioUnidad = scanner.nextDouble()
                scanner.nextLine() // Consumir el salto de línea pendiente
                println("La fruta es organica? (true/false):")
                val esOrganica = scanner.nextBoolean()
                scanner.nextLine() // Consumir el salto de línea pendiente
                println("Ingrese el tipo de fruta")
                val tipoFruta = scanner.nextLine()
                scanner.nextLine() // Consumir el salto de línea pendiente

                val frutas = Frutas(nombreFruta, cantidad, precioUnidad, esOrganica, tipoFruta)
                frutasCRUD.crearFrutas(frutas)
            }
            3 -> {
                println("=== Fruterias ===")
                fruteriaCRUD.listarFruterias()
                println("================")
            }
            4 -> {
                println("=== Frutas ===")
                frutasCRUD.listarFrutas()
                println("===================")
            }
            5 -> {
                println("Ingrese el codigo de la fruteria a actualizar:")
                val index = scanner.nextInt()
                scanner.nextLine() // Consumir el salto de línea pendiente

                val fruterias = fruteriaCRUD.cargarFruterias()
                if (index >= 0 && index < fruterias.size) {
                    println("Ingrese el nombre de la fruteria:")
                    val nombre = scanner.nextLine()
                    println("Ingrese la direccion de la fruteria:")
                    val direccion = scanner.nextLine()
                    println("Ingrese la cantidad de empleados de la fruteria:")
                    val cantidadEmpleados = scanner.nextInt()
                    scanner.nextLine() // Consumir el salto de línea pendiente
                    println("La Fruteria está abierta? (true/false):")
                    val estaAbierta = scanner.nextBoolean()
                    scanner.nextLine() // Consumir el salto de línea pendiente
                    println("Ingrese los ingresos semanales de la fruteria:")
                    val ingresosSemanales = scanner.nextDouble()
                    scanner.nextLine() // Consumir el salto de línea pendiente

                    val fruteria= Fruteria(nombre, direccion, cantidadEmpleados, estaAbierta, ingresosSemanales)
                   fruteriaCRUD.actualizarFruteria(index, fruteria)
                } else {
                    println("El codigo de la fruteria ingresada es inválida.")
                }
            }
            6 -> {
                println("Ingrese el codigo de la fruta a actualizar:")
                val index = scanner.nextInt()
                scanner.nextLine() // Consumir el salto de línea pendiente

                val frutas = frutasCRUD.cargarFrutas()
                if (index >= 0 && index < frutas.size) {
                    println("Ingrese el nombre de la fruta:")
                    val nombreFruta = scanner.nextLine()
                    println("Ingrese la cantidad de fruta:")
                    val cantidad = scanner.nextInt()
                    println("Ingrese el precio por unidad de la fruta:")
                    val precioUnidad = scanner.nextDouble()
                    scanner.nextLine() // Consumir el salto de línea pendiente
                    println("La fruta es organica? (true/false):")
                    val esOrganica = scanner.nextBoolean()
                    scanner.nextLine() // Consumir el salto de línea pendiente
                    println("Ingrese el tipo de fruta")
                    val tipoFruta = scanner.nextLine()
                    scanner.nextLine() // Consumir el salto de línea pendiente

                    val frutas = Frutas(nombreFruta, cantidad, precioUnidad, esOrganica, tipoFruta)
                    frutasCRUD.actualizarFrutas(index, frutas)
                } else {
                    println("El codigo de la fruta ingresada es inválida.")
                }
            }
            7 -> {
                println("Ingrese el codigo de la fruteria a eliminar:")
                val index = scanner.nextInt()
                scanner.nextLine() // Consumir el salto de línea pendiente

                val fruteria = fruteriaCRUD.cargarFruterias()
                if (index >= 0 && index < fruteria.size) {
                    fruteriaCRUD.eliminarFruteria(index)
                } else {
                    println("El codigo de la fruteria ingresada es inválida.")
                }
            }
            8 -> {
                println("Ingrese el codigo de la fruta a eliminar:")
                val index = scanner.nextInt()
                scanner.nextLine() // Consumir el salto de línea pendiente

                val frutas = frutasCRUD.cargarFrutas()
                if (index >= 0 && index < frutas.size) {
                    frutasCRUD.eliminarFrutas(index)
                    println("La fruta fue eliminada exitosamente.")
                } else {
                    println("El codigo de la fruta ingresada es inválida.")
                }
            }
            9 -> break@loop
            else -> println("Opción inválida.")
        }
    }

    println("¡Gracias por la Interaccion!")
}
