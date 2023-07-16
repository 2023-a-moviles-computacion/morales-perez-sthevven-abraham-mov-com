package com.example.examen01_moviles

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperFruteria(
    contexto: Context?,
) : SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaFruteria =
            """
                CREATE TABLE FRUTERIA(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                direccion VARCHAR(50),
                cantidadEmpleados INTEGER,
                estaAbierto VARCHAR(50),
                ingresosSemanales DOUBLE
                )
            
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaFruteria)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun crearFruteria(
        nombre: String,
        direccion: String,
        cantidadEmpleados: Int,
        estaAbierto: String,
        ingresosSemanales: Double

    ): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("direccion", direccion)
        valoresAGuardar.put("cantidadEmpleados", cantidadEmpleados)
        valoresAGuardar.put("esta Abierto", estaAbierto)
        valoresAGuardar.put("ingresos semanales", ingresosSemanales)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "FRUTERIA",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true

    }

    fun eliminarFruteriaFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "FRUTERIA",
                "id=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarFruteriaFormulario(
        nombre: String,
        direccion: String,
        cantidadEmpleados: Int,
        estaAbierto: String,
        ingresosSemanales: Double,
        id: Int,
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar .put("direccion", direccion)
        valoresAActualizar .put("cantidadEmpleados", cantidadEmpleados)
        valoresAActualizar .put("esta Abierto", estaAbierto)
        valoresAActualizar .put("ingresos semanales", ingresosSemanales)
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadosActualizacion = conexionEscritura
            .update(
                "FRUTERIA",
                valoresAActualizar,
                "id=?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if (resultadosActualizacion.toInt() == -1) false else true
    }

    fun consultarFruteriaPorId(id:Int):BFruteria{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM FRUTERIA WHERE ID = ?
            """.trimIndent()
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura,
        )

        val existeFruteria = resultadoConsultaLectura.moveToFirst()
        val fruteriaEncontrada = BFruteria(0, "", "", 0, "", 0.0)
        val arreglo = arrayListOf<BFruteria>()
        if(existeFruteria){
            do{
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val direccion = resultadoConsultaLectura.getString(2)
                val cantidadEmpleados = resultadoConsultaLectura.getInt(3)
                val estaAbierto = resultadoConsultaLectura.getString(4)
                val ingresosSemanales = resultadoConsultaLectura.getDouble(5)
                if(id != null){
                    fruteriaEncontrada.id = id
                    fruteriaEncontrada.nombre = nombre.toString()
                    fruteriaEncontrada.direccion = direccion.toString()
                    fruteriaEncontrada.cantidadEmpleados = cantidadEmpleados
                    fruteriaEncontrada.estaAbierto = estaAbierto.toString()
                    fruteriaEncontrada.ingresosSemanales = ingresosSemanales

                }

            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return fruteriaEncontrada
    }

    fun obtenerTodasLasFruterias(): ArrayList<BFruteria> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
        SELECT * FROM FRUTERIA
    """.trimIndent()
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, null)

        val arregloFruterias = ArrayList<BFruteria>()

        if (resultadoConsultaLectura.moveToFirst()) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val direccion = resultadoConsultaLectura.getString(2)
                val cantidadEmpleados = resultadoConsultaLectura.getInt(3)
                val estaAbierto = resultadoConsultaLectura.getString(4)
                val ingresosSemanales = resultadoConsultaLectura.getDouble(5)

                val fruteria = BFruteria(id, nombre, direccion, cantidadEmpleados, estaAbierto, ingresosSemanales)
                arregloFruterias.add(fruteria)
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()

        return arregloFruterias
    }


}