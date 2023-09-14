package com.example.examen02_firestore

import android.os.Parcel
import android.os.Parcelable

//Parceable para enviar y recibir entre componentes, facilita transferencia y persistencia
class Fruterias(
    public var id: String?,
    public var nombre: String?,
    public var direccion: String?,
    public var numeroEmpleados: Long?,
    public var estaAbierto: Boolean?,
    public var ingresosSemanales: Double?
) : Parcelable {  //la clase es capaz de ser "parcelada" o "desparcelada". La serialización y deserialización de objetos se utiliza para transferir objetos entre componentes de la aplicación o para almacenar objetos

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readDouble()
    )


    override fun toString(): String {
        return "${nombre} - ${direccion} - ${numeroEmpleados} - ${estaAbierto}  - ${ingresosSemanales} "
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nombre)
        parcel.writeString(direccion)
        parcel.writeValue(numeroEmpleados)
        parcel.writeValue(estaAbierto)
        parcel.writeDouble(ingresosSemanales!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Fruterias> {
        override fun createFromParcel(parcel: Parcel): Fruterias {
            return Fruterias(parcel)
        }

        override fun newArray(size: Int): Array<Fruterias?> {
            return arrayOfNulls(size)
        }
    }
}