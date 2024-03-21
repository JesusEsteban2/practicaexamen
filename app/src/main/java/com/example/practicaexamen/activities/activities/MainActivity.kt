package com.example.practicaexamen.activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaexamen.R
import com.example.practicaexamen.activities.Datos.Movimiento
import com.example.practicaexamen.databinding.ActivityMainBinding
import com.example.preacticaexamen.activities.adapter.ReciclerAdapter



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: ReciclerAdapter

    /**
     * on Create se ejecuta al iniciar la Actividad
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enicializar binding para acceso a los componentes
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener de la BBDD datos y saldo
        dataSet =Movimiento().queryAll(binding.root.context)
        var saldo=Movimiento().sumaCantidad(binding.root.context)

        //Inicializar adapter
        adapter = ReciclerAdapter{ onClick(it) }

        // Asignar el adapter al reciclerView
        binding.reciclerW.adapter = adapter
        binding.reciclerW.layoutManager = LinearLayoutManager(this)

    }

    override fun onResume() {
        super.onResume()
        dataSet =Movimiento().queryAll(binding.root.context)
        updateView(dataSet,adapter)
    }

    /**
     * Función OnClick para el adapter del ReciclerView
     * Recibe la posición (Elemento) al que se ha hecho click y carga la
     * pantalla de detalle (DetaidActivity) de dicho elemento.
     */
    fun onClick(posi:Int){
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    /**
     * Actualiza el Reciclerview
     * @param data: Nuevos datos con los que actualizar la vista
     * @param adap: Adapter de la vista
     */
    fun updateView(data:List<Movimiento>,adap:ReciclerAdapter){
        adap.updateItems(data)
    }


    // Objeto para almacenar los datos a mostrar en ReciclerView
    companion object{
        var dataSet: List<Movimiento> = listOf<Movimiento>()
    }

}