package com.example.practicaexamen.activities.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaexamen.R
import com.example.practicaexamen.activities.Datos.Movimiento
import com.example.practicaexamen.databinding.ActivityAddBinding
import com.example.practicaexamen.databinding.ActivityAddBinding.*
import java.text.SimpleDateFormat
import java.util.Date

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add)

        // Enicializar binding para acceso a los componentes
        var bindin = inflate(layoutInflater)
        setContentView(bindin.root)

        bindin.guardarButton.setOnClickListener({onClickSave(bindin)})

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val resultdate = Date(System.currentTimeMillis())
        val f=sdf.format(resultdate)

        bindin.fec.setText(f)


    }

    fun onClickSave(b:ActivityAddBinding){



        var f=b.fec.text.toString()

        var c=b.cant.text.toString().toFloat()
        val mov= Movimiento(0,c,f)
        mov.insert(b.root.context,mov)
        finish()
    }

}