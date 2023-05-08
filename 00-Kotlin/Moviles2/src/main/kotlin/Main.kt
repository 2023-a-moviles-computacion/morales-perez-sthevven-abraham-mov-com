import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    println("Hello World!")

    // tipos de variable

    // Imutables(no se reasignan "=")
    val inmutable: String = "Adrian";
    // inmutable="vicente";

    // mutables( re asignar)
    val mutable: String = "Vicente";
    //mutable="Adrian";

    // val > var

    // Duck Typing
    var ejemploVariable = "Adrian Eguez"
    val edadEjemplo: Int = 12
    ejemploVariable.trim()
    // ejemploVariable=edadEjemplo;

    // variable primitiva
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estdoCivil: Char = 'C'
    val mayorEdad: Boolean = true
    // clase Java
    val fechaNacimiento: Date = Date()

    // SWITCH
    val estadoCivilWhen = "C"
    when (estadoCivilWhen) {
        ("C") -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }
    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No"



fun imprimirNombre(nombre:String): Unit {
    println("Nombre: ${nombre}")
}


fun calcularSueldo(
    sueldo:Double, //requerido
    tasa: Double=12.00, //OPcional (defecto)
    bonoEspecial: Double? = null, //Opcion null -> nullable

):Double{
    // Int -> Int? (nullable)
    // String -> String? (nullable)
    // Date -> Date? (nullable)
    if (bonoEspecial==null){
        return sueldo * (100/tasa)
    }else {
        return sueldo * (100/tasa)+bonoEspecial
    }
}

//clases
abstract class NumerosJava{
    protected val numeroUno:Int
    private val numeroDos:Int
    constructor(
        uno:Int,
        dos:Int
    ){// bloque de codigo del constructor
        this.numeroUno=uno
        this.numeroDos=dos
        println("Inicializando")
    }
}

abstract class Numeros(// cuando abrimos el parentesis estamos ya habalndo del constructor primario
   //ejemplo
   //uno: Int, (Parametro(sin modificador de acceso))
   //private var uno:Int, //propiedad publica clase numeros.uno
   //var uno: Int,// propiedad de la clase (por defecto es PUBLIC
   //public var uno:Int,
   protected  val numeroUno:Int, //Propiedad de la clase protected numeros.numeroUno
   protected  val numeroDos:Int, //Propiedad de la clase protected numeros.numeroDos
){
   // var cedula: string ="" (public es por defecto)
    // private valorCalculado: Int=0 (private)
    init{
        this.numeroUno;this.numeroDos; //this es opcional
       numeroUno;numeroDos; //sin el this , es lo mismo
       println("Inicializando")
    }
}
// aqui hay que poner lo que se hizo el lunes 29 de mayo del 2023 sobre la clase








//

//Temas Arreglos

//tipos de arreglo

//arreglo estaticos
val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
println(arregloEstatico)

//arreglo dinamicos
val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
println(arregloDinamico)
arregloDinamico.add(11)
arregloDinamico.add(12)
println(arregloDinamico)

//operadores -> sirven para los arreglos estaticas y dinamicos
// FIOR EACH -> Unit
// Iterar un arreglo
val respuestaForEach: Unit = arregloDinamico
    .forEach{valorActual: Int ->
        println("Valor actual:${valorActual}")
    }
arregloDinamico.forEach{println(it)} // it en ingles significa el elemento

arregloEstatico
    .forEachIndexed{indice: Int, valorActual: Int ->
        println("Valor ${valorActual} Indice: ${indice}")
    }
    println(respuestaForEach)

// MAP -> Muta el arreglo (cambia el arreglo)
// 1) Enviamos el nuevo valor de la iteracion
// 2) Nos devuelve es un NUEVO ARREGLO con los valores modificados

val respuestaMap: List<Double> = arregloDinamico
    .map {valorActual: Int ->
        return@map valorActual.toDouble() + 100.00
    }
println(respuestaMap)
val respuestaMapDos = arregloDinamico.map {it +15}

// Filter -> FILTRAR  EL ARREGLO
// 1) devolver una expresion (true o false)
// 2) nuevo arreglo filtrado

 val respuestaFilter:List<Int> = arregloDinamico
     .filter{valorActual: Int->
         val mayoresACinco: Boolean = valorActual > 5 //expresion condicion
         return@filter mayoresACinco
     }
 val respuestaFilterDos = arregloDinamico.filter{it <=5}
 println(respuestaFilter)
 println(respuestaFilterDos)

 //OR AND
 //OR-> ANY(ALGUNO CUMPLE?)
 //AND -> ALL(TODOS CUMPLEN?)

 val respuestaAny: Boolean = arregloDinamico
     .any {valorActual: Int ->
         return@any(valorActual >5)
     }
    println(respuestaAny)//true
    val respuestaAll: Boolean = arregloDinamico
        .all {valorActual: Int ->
            return@all(valorActual >5)
        }
    println(respuestaAll)//false
 //REDUCE -> VALOR ACUMULADO
 //valor acumulado =0 (siempre 0 en lenguaje Kotlin)
 //[1,2,3,4,5] -> sume todos los valores del arreglo
 //valorIteracion1 = valorEmpieza +1 =0+1 =1 ->Iteracion 1
//valorIteracion2 = valorInteracion1 +2 =1+2 =3 ->Iteracion 2
//valorIteracion3 = valorInteracion2 +3 =3+3 =6 ->Iteracion 3
//valorIteracion4 = valorInteracion3 +4 =6+4 =10 ->Iteracion 4
//valorIteracion5 = valorInteracion4 +5=10+5 =15 ->Iteracion 5

val respuestaReduce : Int= arregloDinamico
    .reduce{//acumulado =0 ->siempre empieza en 0
        acumulado:Int, valorActual:Int->
        return@reduce (acumulado +valorActual)// ->logica NEGOCIO
     }
    println(respuestaReduce) //78

}