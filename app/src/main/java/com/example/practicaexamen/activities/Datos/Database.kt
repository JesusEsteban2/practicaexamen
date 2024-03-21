package com.example.practicaexamen.activities.Datos


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Database (context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES_MOV)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        destroyDatabase(db)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        destroyDatabase(db)
        onCreate(db)
    }

    private fun destroyDatabase(db: SQLiteDatabase?){
        db?.execSQL(SQL_DELETE_TABLE_MOV)
    }

    /**
     * Insert new row in DB, returning true if the primary key value is over 0
     * @param r Recipe to insert in DB
     * @return true if insert
     */
    fun insertMovimiento(r:Movimiento):Boolean{

        // Create a new map of values, where column names are the key
        val values= asigValues(r)

        // Insert the new row, returning true if the primary key value is over 0
        val db=this.writableDatabase
        val newRowId = db.insert(TABLE_NAME, null, values)
        db.close()
        return (newRowId>0)
    }


    /**
     * Delete record by Id
     * @param i: Id of Move to delete
     * @return: True if the delete is done.
     */
    fun deleteById(i:Int):Boolean{

        // Delete row by id
        val db=this.writableDatabase
        val rowDel = db.delete(TABLE_NAME, "ID=$i",null)
        db.close()
        Log.i("DB","Eliminado $rowDel registro")
        return (rowDel>0)
    }


    /**
     * Devuelve una lista con 1 elemento que corresponde con el ID Buscado
     * @param k Id a buscar
     * @return Lista con las recetas
     */
    fun searchById(k:Int): List<Movimiento>? {

        // Search by id
        val av=arrayOf<String>(k.toString())

        val db=this.writableDatabase
        val cursor: Cursor = db.query(TABLE_NAME,null,"ID = ?",av,
            null,null,null)


        // Llama a la función para crear la lista con los datos recuperados
        val lm:MutableList<Movimiento> = recupDatos(cursor)

        // Cierra cursor y BBDD
        cursor.close()
        db.close()

        return lm
    }

    /**
     * Devuelve la suma de las cantidades de los movimientos en la BD
     * @param res resultado de la suma de cantidades
     * @return Float con el importe de la suma
     */
    @SuppressLint("Range")
    fun sumnaCantidad():Float{

        val db=this.writableDatabase
        var res:Float=0F
        var sqlSentence = "SELECT SUM(cantidad) as Total FROM "+TABLE_NAME

        val cur = db.rawQuery(sqlSentence, null)

        if (cur.moveToFirst()) {
            if(cur.getColumnIndex("Total")>-1) {
                res = cur.getFloat(cur.getColumnIndex("Total"))
            }
        }

        cur.close()
        db.close()
        return res
    }

    /**
     * Devuelve una lista con todos los elementos de la BBDD
     * @return Lista con las recetas
     */
    fun searchAll():List<Movimiento>{

        val db=this.writableDatabase
        val cursor:Cursor = db.query(TABLE_NAME,null,null,null,
            null,null,null)

        // Llama a la función para crear la lista con los datos recuperados
        val lm:MutableList<Movimiento> = recupDatos(cursor)

        // Cierra cursor y BBDD
        cursor.close()
        db.close()

        return lm
    }

    /**
     * Crea una lista de movimientos con los datos devueltos por la base de datos.
     * @param c Cursor de la BBDD
     * @return Lista de Movimientos MutableList<Movimiento>
     */
    fun recupDatos (c:Cursor):MutableList<Movimiento>{

        val lm:MutableList<Movimiento> = mutableListOf<Movimiento>()

        while (c.moveToNext()) {
            val m=Movimiento(c.getInt(0),c.getFloat(1),
                c.getString(2))
            lm.add(m)
        }

        return lm
    }



    /**
     * Crea un ContentValues (Clave,Valor) con los nombres de los campos de la BD y
     * los valores pasados en la receta.
     * @param mo Movimiento para asignar los valores.
     * @return ContentValues (Clave,Valor) (Columna DB,Valor de Movimiento)
     */
    fun asigValues(mo:Movimiento):ContentValues{

        val values=ContentValues()

        values.put(SQL_MOV_COLUMS[0],mo.cantidad)
        values.put(SQL_MOV_COLUMS[1],mo.fecha)


        return values
    }

    companion object {
        const val DATABASE_NAME="Movimientos.db"
        const val DATABASE_VERSION=1
        const val TABLE_NAME="Importes"
        const val SQL_DELETE_TABLE_MOV="DROP TABLE IF EXISTS $TABLE_NAME"
        const val SQL_CREATE_ENTRIES_MOV ="CREATE TABLE $TABLE_NAME "+
                "(Id INTEGER PRIMARY KEY AUTOINCREMENT,cantidad FLOAT,fecha String)"
        val SQL_MOV_COLUMS:List<String> = listOf("cantidad","fecha")
    }
}