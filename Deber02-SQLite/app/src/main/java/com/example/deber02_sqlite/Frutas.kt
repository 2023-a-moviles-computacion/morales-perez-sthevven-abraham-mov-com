package com.example.deber02_sqlite
import android.os.Parcel
import android.os.Parcelable

class Frutas(
    var codigoFruteria :Int?,
    var nombreFruta: String?,
    var cantidad: Int?,
    var precioUnidad: Double?,
    var esOrganica: Boolean?,
    var tipoFruta: String?
) : Parcelable {
    override fun toString(): String {
        return "${nombreFruta}\n\tCantidad: ${cantidad}\n\tPrecio Unidad: ${precioUnidad}\n\tEs Organica?: ${esOrganica}\n\tTipo de Fruta: ${tipoFruta}"
    }

    fun crearFrutas(nombreFruta: String,cantidad: Int,precioUnidad: Double,esOrganica: Boolean,tipoFruta: String) {
        this.nombreFruta = nombreFruta
        this.cantidad = cantidad
        this.precioUnidad = precioUnidad
        this.esOrganica = esOrganica
        this.tipoFruta = tipoFruta
    }


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(codigoFruteria!!)
        parcel.writeString(nombreFruta)
        parcel.writeInt(cantidad!!)
        parcel.writeDouble(precioUnidad!!)
        parcel.writeByte(if (esOrganica!!) 1 else 0)
        parcel.writeString(tipoFruta)
    }

    override fun describeContents(): Int {
        return 0
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