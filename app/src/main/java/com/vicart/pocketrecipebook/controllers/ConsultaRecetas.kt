package com.vicart.pocketrecipebook.controllers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vicart.pocketrecipebook.R
import com.vicart.pocketrecipebook.adapters.RecetaAdapter
import com.vicart.pocketrecipebook.room_data.db_info.DatabaseInfo
import com.vicart.pocketrecipebook.room_data.entity.Receta
import kotlinx.android.synthetic.main.activity_consulta_recetas.*

class ConsultaRecetas: AppCompatActivity(){
    var arrayRecetas = ArrayList<Receta>()
    var tipoSeleccionado = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_recetas)

        llenarRecycleView()

        btnAgregarReceta.setOnClickListener {
            val intent = Intent(this, RegistrarReceta::class.java)
            intent.putExtra("categoriaReceta", tipoSeleccionado)
            startActivity(intent)
        }
    }

    private fun llenarRecycleView(){
        val bundle = intent.extras
        tipoSeleccionado = bundle?.getString("categoriaReceta")!!
        listaRecetas.layoutManager = LinearLayoutManager(this)
        listaRecetas.setHasFixedSize(true)
        val database = DatabaseInfo.getDatabase(this)
        database.recetas().getRecetasPorCategoria(tipoSeleccionado?:" ").observe(this, Observer {
            arrayRecetas = it as ArrayList<Receta>
            listaRecetas.adapter = RecetaAdapter(arrayRecetas)
        })
    }
}