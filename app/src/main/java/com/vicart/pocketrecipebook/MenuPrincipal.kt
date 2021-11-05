package com.vicart.pocketrecipebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vicart.pocketrecipebook.controllers.ConsultaRecetas
import com.vicart.pocketrecipebook.controllers.Navegacion
import kotlinx.android.synthetic.main.activity_main.*

class MenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCarnes.setOnClickListener {
            val intent = Intent(this, ConsultaRecetas::class.java)
            intent.putExtra("categoriaReceta", "Carnes")
            startActivity(intent)
        }

        btnEnsaladas.setOnClickListener {
            val intent = Intent(this, ConsultaRecetas::class.java)
            intent.putExtra("categoriaReceta", "Ensaladas")
            startActivity(intent)
        }

        btnMariscos.setOnClickListener {
            val intent = Intent(this, ConsultaRecetas::class.java)
            intent.putExtra("categoriaReceta", "Mariscos")
            startActivity(intent)
        }

        btnSopas.setOnClickListener {
            val intent = Intent(this, ConsultaRecetas::class.java)
            intent.putExtra("categoriaReceta", "Sopas")
            startActivity(intent)
        }

        btnPostre.setOnClickListener {
            val intent = Intent(this, ConsultaRecetas::class.java)
            intent.putExtra("categoriaReceta", "Postres")
            startActivity(intent)
        }

        btnNavegacion.setOnClickListener {
            val intent = Intent(this, Navegacion::class.java)
            startActivity(intent)
        }
    }
}