package com.example.practicaexamen.activities.Datos

import android.content.Context

class Movimiento (var id:Int,var cantidad:Float,var fecha:Float) {

    /**
     * Función para insertar una lista de Movimientos en la BBDD
     * @param context Contexto de la operación
     * @return true si no ha habido errores
     */

    fun insert(context: Context, m:Movimiento):Boolean {
        var db= Database(context)
        var ok:Boolean=false

        ok = db.insertMovimiento(m)

        return ok
    }

    fun queryAll(context:Context):List<Movimiento> {
        var db= Database(context)

        val lm=db.searchAll()

        return lm
    }

    fun sumaCantidad(context:Context): Float {
        var db= Database(context)

        val lm=db.sumnaCantidad()

        return lm
    }

}