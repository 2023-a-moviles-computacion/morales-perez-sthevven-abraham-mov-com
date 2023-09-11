package com.example.deber02_sqlite
import android.os.Parcel
import android.os.Parcelable

//Parceable para enviar y recibir entre componentes, facilita transferencia y persistencia
class Fruterias(
    var id: Int,
    var nombre: String?,
    var direccion: String?,
    var cantidadEmpleados: Int?,
    var estaAbierto: Boolean?,
    var ingresosSemanales: Double?
) : Parcelable {  //la clase es capaz de ser "parcelada" o "desparcelada". La serialización y deserialización de objetos se utiliza para transferir objetos entre componentes de la aplicación o para almacenar objetos
    override fun toString(): String {
        return "Nombre Fruteria: ${nombre}\n\t Direccion: ${direccion}\n\t Cantidad Empleados: ${cantidadEmpleados}\n\t Esta Abierto? ${estaAbierto}\n\t  Ingresos Semanales: ${ingresosSemanales}"
    }

    fun crearFruteria(nombre: String, direccion: String, cantidadEmpleados: Int,estaAbierto: Boolean,ingresosSemanales: Double) {
        this.nombre = nombre
        this.direccion = direccion
        this.cantidadEmpleados= cantidadEmpleados
        this.estaAbierto = estaAbierto
        this.ingresosSemanales = ingresosSemanales
    }


    constructor(parcel: Parcel) : this( //Un Parcel es un contenedor de datos que se utiliza para enviar y recibir objetos
        parcel.readInt(),               //Este constructor se utiliza para deserializar un objeto Fruterias a partir de un Parcel
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) { //para escribir los valores de las propiedades de la clase en un Parcel
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(direccion)
        parcel.writeInt(cantidadEmpleados!!)
        parcel.writeByte(if (estaAbierto!!) 1 else 0)
        parcel.writeDouble(ingresosSemanales!!)
    }

    override fun describeContents(): Int { //devuelve un valor entero que describe el tipo de objetos especiales contenidos en el Parcel.
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Fruterias> { //para crear instancias de la clase Continente a partir de un Parcel
        override fun createFromParcel(parcel: Parcel): Fruterias{
            return Fruterias(parcel)
        }

        override fun newArray(size: Int): Array<Fruterias?> {
            return arrayOfNulls(size)
        }
    }
}

