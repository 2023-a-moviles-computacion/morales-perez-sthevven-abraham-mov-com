package com.example.examen02_firestore

import android.os.Parcel
import android.os.Parcelable


class Frutas(
    public var id: String?,
    public var nombre: String?,
    public var cantidad: Long?,
    public var precioUnitario: Double?,
    public var esOrganica: Boolean?,
    public var tipoFruta: String?,
    public var fruteriaId: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readString()
    )

    override fun toString(): String {
        return "${nombre} - ${cantidad} - ${precioUnitario} - ${esOrganica} - ${tipoFruta}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(id)
        parcel.writeString(nombre)
        parcel.writeValue(cantidad)
        parcel.writeValue(precioUnitario)
        parcel.writeValue(esOrganica)
        parcel.writeValue(tipoFruta)
        parcel.writeString(fruteriaId)
    }

    companion object CREATOR : Parcelable.Creator<Frutas> {
        override fun createFromParcel(parcel: Parcel): Frutas {
            return Frutas(parcel)
        }

        override fun newArray(size: Int): Array<Frutas?> {
            return arrayOfNulls(size)
        }
    }
}