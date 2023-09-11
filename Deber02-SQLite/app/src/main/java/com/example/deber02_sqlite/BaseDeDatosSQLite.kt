package com.example.deber02_sqlite
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*

class BaseDeDatosSQLite (context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Fruteria-Frutas"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Crear las tablas necesarias en la base de datos
        val createFruteriasTableQuery = """CREATE TABLE IF NOT EXISTS FRUTERIAS
                (id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50), 
                direccion VARCHAR(50),
                cantidadEmpleados INTEGER,
                estaAbierto VARCHAR(50),
                ingresosSemanales DOUBLE)""".trimIndent()

        val createFrutasTableQuery = """CREATE TABLE IF NOT EXISTS FRUTAS 
                (IdFruteria INTEGER PRIMARY KEY, 
                nombre VARCHAR(50), 
                cantidad INTEGER,
                precioUnidad DOUBLE,
                esCapital VARCHAR(50),
                tipoFruta VARCHAR(50))""".trimIndent()

        db?.execSQL(createFruteriasTableQuery)
        db?.execSQL(createFrutasTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
    // Métodos para la tabla de continentes

    fun insertFruteria(fruterias: Fruterias): Long {
        val db = writableDatabase

        val values = ContentValues().apply {
            put("Nombre Fruteria", fruterias.nombre)
            put("Direccion", fruterias.direccion)
            put("Cantidad de Empleados", fruterias.cantidadEmpleados)
            put("Esta Abierto", fruterias.estaAbierto)
            put("Ingresos", fruterias.ingresosSemanales)
        }

        val id = db.insert("FRUTERIAS", null, values)
        db.close()

        return id
    }

    fun updateFruteria(fruterias: Fruterias): Int {
        val db = writableDatabase



        val values = ContentValues().apply {
            put("Nombre Fruteria", fruterias.nombre)
            put("Direccion", fruterias.direccion)
            put("Cantidad de Empleados", fruterias.cantidadEmpleados)
            put("Esta Abierto", fruterias.estaAbierto)
            put("Ingresos", fruterias.ingresosSemanales)
        }

        val rowsAffected = db.update("FRUTERIAS", values, "idFRUTERIAS = ?", arrayOf(fruterias.id.toString()))
        db.close()

        return rowsAffected
    }

    fun deleteFruteria(fruteriaId: Int): Int {
        val db = writableDatabase

        val rowsAffected = db.delete("FRUTERIAS", "idFRUTERIAS = ?", arrayOf(fruteriaId.toString()))
        db.close()

        return rowsAffected
    }


    @SuppressLint("Range")
    fun getAllFruterias(): List<Fruterias> {
        val db = readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM FRUTERIAS", null)

        val fruterias = mutableListOf<Fruterias>()
        cursor?.let {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(it.getColumnIndex("idFruterias"))
                    val nombre = it.getString(it.getColumnIndex("Nombre Fruteria"))
                    val direccion = it.getString(it.getColumnIndex("Direccion"))
                    val cantidadEmpleados = it.getInt(it.getColumnIndex("Cantidad de Empleados"))
                    val estaAbierto=it.getInt(it.getColumnIndex("Esta Abierto?")) ==1
                    val ingresos=it.getDouble(it.getColumnIndex("Ingresos Semanales"))
                    val fruteria = Fruterias(id, nombre, direccion,cantidadEmpleados,estaAbierto,ingresos)
                    fruterias.add(fruteria)
                } while (it.moveToNext())
            }
        }

        cursor?.close()
        db.close()

        return fruterias
    }

    fun insertFrutas(frutas: Frutas): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("Nombre Fruta", frutas.nombreFruta)
            put("Cantidad", frutas.cantidad)
            put("Precio Unitario", frutas.precioUnidad)
            put("Es organica?", frutas.esOrganica)
            put("Tipo de Fruta", frutas.tipoFruta)
        }

        val id = db.insert("FRUTAS", null, values)
        db.close()

        return id
    }

    fun updateFrutas(frutas: Frutas): Int {
        val db = writableDatabase


        val values = ContentValues().apply {
            put("Nombre Fruta", frutas.nombreFruta)
            put("Cantidad", frutas.cantidad)
            put("Precio Unitario", frutas.precioUnidad)
            put("Es organica?", frutas.esOrganica)
            put("Tipo de Fruta", frutas.tipoFruta)
        }


        val rowsAffected = db.update("FRUTAS", values, "idFruta = ?", arrayOf(frutas.codigoFruteria.toString()))
        db.close()

        return rowsAffected
    }

    fun deleteFruta(frutaId: Int): Int {
        val db = writableDatabase

        val rowsAffected = db.delete("FRUTAS", "idFruta = ?", arrayOf(frutaId.toString()))
        db.close()

        return rowsAffected
    }

    @SuppressLint("Range")
    fun getFrutasByFruteria(fId: Int): List<Frutas> {
        val db = readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM FRUTAS WHERE fruteriaId = ?", arrayOf(fId.toString()))
        //registros de la tabla "FRUTAS" que coincidan con un determinado "fruteriaId",  se almacena en un objeto Cursor

        val frutas = mutableListOf<Frutas>()
        cursor?.let {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(it.getColumnIndex("id FRUTA"))
                    val nombre = it.getString(it.getColumnIndex("nombre de la fruta"))
                    val cantidad = it.getInt(it.getColumnIndex("Cantidad"))
                    val precioUnitario = it.getDouble(it.getColumnIndex("Precio"))
                    val esOrganica = it.getInt(it.getColumnIndex("es organica")) == 1
                    val tipoFruta = it.getString(it.getColumnIndex("Tipo de fruta"))

                    val fruta = Frutas(id, nombre, cantidad, precioUnitario, esOrganica,tipoFruta)
                    frutas.add(fruta)
                } while (it.moveToNext())
            }
        }

        cursor?.close()
        db.close()

        return frutas
    }
}