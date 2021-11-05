package com.vicart.pocketrecipebook.controllers

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vicart.pocketrecipebook.R
import com.vicart.pocketrecipebook.adapters.IngredienteAdapter
import com.vicart.pocketrecipebook.room_data.db_info.DatabaseInfo
import com.vicart.pocketrecipebook.room_data.entity.Ingrediente
import com.vicart.pocketrecipebook.room_data.entity.Receta
import kotlinx.android.synthetic.main.activity_info_receta.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InfoReceta: AppCompatActivity() {

    lateinit var receta: Receta
    val listaIngredientes = ArrayList<Ingrediente>()
    var ultimoCalculo: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_receta)

        receta = intent.getSerializableExtra("receta")!! as Receta
        llenarRecyclerIngredientes()
        llenarDatos()


        btnEliminarReceta.setOnClickListener {
            val database = DatabaseInfo.getDatabase(this)
            receta.estado = "inactivo"
            CoroutineScope(Dispatchers.IO).launch {
                database.recetas().editarReceta(receta)
            }

            Toast.makeText(this, "Eliminación exitosa", Toast.LENGTH_SHORT).show()
            this.finish()
        }

        btnRegresarConsulta.setOnClickListener {
            finish()
        }

        btnCalcular.setOnClickListener {
            if(txtCalcularPorcion.text.isNotEmpty()){
                for (ingrediente in listaIngredientes){
                    val resultado = (ingrediente.cantidadPorPersona * txtCalcularPorcion.text.toString().toInt()) / ultimoCalculo
                    ingrediente.cantidadPorPersona = resultado
                }
                listaInfoIngredientes.adapter = IngredienteAdapter(listaIngredientes)
                ultimoCalculo = txtCalcularPorcion.text.toString().toInt()
            } else {
                Toast.makeText(this, "Ingrese una cantidad para calcular", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun llenarDatos(){
        lblInfoNombreReceta.text = receta.nombre
        lblInfoProcedimiento.text = receta.preparacion
    }

    private fun llenarRecyclerIngredientes(){
        listaInfoIngredientes.layoutManager = LinearLayoutManager(this)
        val database = DatabaseInfo.getDatabase(this)

        database.ingredientes().getAllByReceta(receta.idReceta).observe(this, Observer {
            println(it.size)

            for(ingrediente in it){
                listaIngredientes.add(ingrediente)
            }

            listaInfoIngredientes.adapter = IngredienteAdapter(listaIngredientes)
        })
    }
}