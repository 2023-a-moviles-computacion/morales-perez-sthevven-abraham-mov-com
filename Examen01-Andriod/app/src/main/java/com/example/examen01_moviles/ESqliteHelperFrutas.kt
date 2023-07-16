package com.example.examen01_moviles

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperFrutas(
    contexto: Context?,
) : SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaFrutas =
            """
                CREATE TABLE FRUTAS(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombreFruta VARCHAR(50),
                cantidad INT,
                precioUnidad DOUBLE,
                esOrganica VARCHAR(10),
                tipoFruta VARCHAR(50),
                FOREIGN KEY (fruteriaID) REFERENCES FRUTERIA(id) ON DELETE CASCADE 
                )
            
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaFrutas)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun crearFrutas(
        nombreFruta: String,
        cantidad: Int,
        precioUnidad: Double,
        esOrganica: String,
        tipoFruta: String,
        fruteriaID: Int
    ): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombreFruta)
        valoresAGuardar.put("cantidad", cantidad)
        valoresAGuardar.put("precio unidad", precioUnidad)
        valoresAGuardar.put("es organica", esOrganica)
        valoresAGuardar.put("tipo de fruta", tipoFruta)
        valoresAGuardar.put("fruteriaID", fruteriaID)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "FRUTAS",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true

    }

    fun eliminarFrutaFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "FRUTA",
                "id=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarFrutaFormulario(
        nombreFruta: String,
        cantidad: Int,
        precioUnidad: Double,
        esOrganica: String,
        tipoFruta: String,
        id: Int
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombreFruta)
        valoresAActualizar.put("cantidad", cantidad)
        valoresAActualizar.put("precio por unidad", precioUnidad)
        valoresAActualizar.put("es organica", esOrganica)
        valoresAActualizar.put("tipo de fruta", tipoFruta)
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadosActualizacion = conexionEscritura
            .update(
                "FRUTA",
                valoresAActualizar,
                "id=?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if (resultadosActualizacion.toInt() == -1) false else true
    }

    fun consultarFrutaPorId(id:Int):BFrutas{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM FRUTA WHERE ID = ?
            """.trimIndent()
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura,
        )

        val existeFruta = resultadoConsultaLectura.moveToFirst()
        val frutaEncontrado = BFrutas(0, "", 0, 0.0, "", "")
        val arreglo = arrayListOf<BFrutas>()
        if(existeFruta){
            do{
                val id = resultadoConsultaLectura.getInt(0)
                val nombreFruta = resultadoConsultaLectura.getString(1)
                val cantidad = resultadoConsultaLectura.getInt(2)
                val precioUnidad = resultadoConsultaLectura.getDouble(3)
                val esOrganica = resultadoConsultaLectura.getString(4)
                val tipoFruta = resultadoConsultaLectura.getString(5)
                if(id != null){
                    frutaEncontrado.id = id
                    frutaEncontrado.nombreFruta = nombreFruta.toString()
                    frutaEncontrado.cantidad = cantidad
                    frutaEncontrado.precioUnidad = precioUnidad
                    frutaEncontrado.esOrganica = esOrganica.toString()
                    frutaEncontrado.tipoFruta = tipoFruta.toString()

                }

            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return frutaEncontrado
    }

    fun obtenerFrutasDeFruterias(fruteriaId: Int): ArrayList<BFrutas> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
        SELECT * FROM FRUTAS WHERE fruteriaId = ?
    """.trimIndent()
        val parametrosConsultaLectura = arrayOf(fruteriaId.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, parametrosConsultaLectura)

        val arregloFrutas = ArrayList<BFrutas>()

        if (resultadoConsultaLectura.moveToFirst()) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val nombreFruta = resultadoConsultaLectura.getString(1)
                val cantidad = resultadoConsultaLectura.getInt(2)
                val precioUnidad = resultadoConsultaLectura.getDouble(3)
                val esOrganica = resultadoConsultaLectura.getString(4)
                val tipoFruta = resultadoConsultaLectura.getString(5)

                val fruta = BFrutas(id, nombreFruta, cantidad, precioUnidad, esOrganica, tipoFruta)
                arregloFrutas.add(fruta)
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()

        return arregloFrutas
    }

}