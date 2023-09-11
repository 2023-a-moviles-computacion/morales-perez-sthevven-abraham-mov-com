package com.example.deber02_sqlite
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

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

    fun crearFrutas(nombreFruta: String?,cantidad: Int?,precioUnidad: Double?,esOrganica: Boolean?,tipoFruta: String?) {
        this.nombreFruta = nombreFruta
        this.cantidad = cantidad
        this.precioUnidad = precioUnidad
        this.esOrganica = esOrganica
        this.tipoFruta = tipoFruta
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readBoolean(),
        parcel.readString()!!
    )

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombreFruta)
        parcel.writeInt(cantidad!!)
        parcel.writeDouble(precioUnidad!!)
        parcel.writeBoolean(esOrganica==true)
        parcel.writeString(tipoFruta)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Frutas> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Frutas {
            return Frutas(parcel)
        }

        override fun newArray(size: Int): Array<Frutas?> {
            return arrayOfNulls(size)
        }
    }
}