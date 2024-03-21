package com.example.practicaexamen.activities.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaexamen.R
import com.example.practicaexamen.databinding.ActivityAddBinding
import com.example.practicaexamen.databinding.ActivityMainBinding

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add)

        // Enicializar binding para acceso a los componentes
        var binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}