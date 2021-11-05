package com.vicart.pocketrecipebook.controllers

import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vicart.pocketrecipebook.R
import com.vicart.pocketrecipebook.adapters.IngredienteAdapter
import com.vicart.pocketrecipebook.adapters.Paso
import com.vicart.pocketrecipebook.adapters.PasoAdapter
import com.vicart.pocketrecipebook.room_data.db_info.DatabaseInfo
import com.vicart.pocketrecipebook.room_data.entity.Ingrediente
import com.vicart.pocketrecipebook.room_data.entity.Receta
import kotlinx.android.synthetic.main.activity_registrar_receta.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrarReceta: AppCompatActivity() {
    private val pasosData = ArrayList<Paso>()
    private val ingredienteData = ArrayList<Ingrediente>()
    private var contadorPasos = 0
    private var contadorIngrediente = 0
    private val ingredientes = ArrayList<Ingrediente>()
    private var idReceta: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_receta)

        val bundle = intent.extras
        val tipoSeleccionado = bundle?.getString("categoriaReceta")

        llenarSpinner()
        llenarRecyclerPasos()
        llenarRecyclerIngredientes()

        btnGuardarReceta.setOnClickListener {
            val database = DatabaseInfo.getDatabase(this)
            if (validarCamposVacios()){
                CoroutineScope(Dispatchers.IO).launch {
                    idReceta = database.recetas().getLastID()+1
                }

                val nombreReceta = txtNombrePlatillo.text.toString()

                var procedimiento = ""

                for (paso in pasosData){
                    procedimiento += "${paso.numeroPaso}. ${paso.paso} \n"
                }

                val nuevaReceta = Receta(nombreReceta, procedimiento, tipoSeleccionado?: " ", "activo")


                for (i in 0 until listaIngredientes.childCount) {
                    postAndNotifyAdapter(Handler(), listaIngredientes, i)
                }

                CoroutineScope(Dispatchers.IO).launch {
                    database.recetas().guardarReceta(nuevaReceta)
                    for (ingrediente in ingredientes){
                        database.ingredientes().guardarIngrediente(ingrediente)
                    }
                }
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor ingrese todos los datos", Toast.LENGTH_SHORT).show()
            }
        }

        btnAgregarIngrediente.setOnClickListener {
            if(validarCamposVaciosIngrediente()){
                val nombreIngrediente = txtNombreIngrediente.text.toString()
                val cantidad = txtCantidad.text.toString().toDouble()
                val tipoMedida = spinnerTiposMedidas.selectedItem.toString()

                val nuevoIngrediente = Ingrediente(nombreIngrediente, cantidad, tipoMedida)
                ingredienteData.add(nuevoIngrediente)
                llenarRecyclerIngredientes()
                limpiarCamposIngrediente()
                contadorIngrediente++
            } else {
                Toast.makeText(this, "Por favor ingrese todos los datos de los ingredientes", Toast.LENGTH_SHORT).show()
            }
        }

        btnAgregarPaso.setOnClickListener {
            if(txtPaso.text.isNotEmpty()){
                contadorPasos++
                var nuevoPaso = Paso(contadorPasos, txtPaso.text.toString())
                pasosData.add(nuevoPaso)
                llenarRecyclerPasos()
                txtPaso.text.clear()
            } else {
                Toast.makeText(this, "Por favor ingrese el paso", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }

    private fun validarCamposVacios(): Boolean{
        return (txtNombrePlatillo.text.isNotEmpty() &&
                contadorPasos > 0 &&
                contadorIngrediente > 0)
    }

    private fun validarCamposVaciosIngrediente(): Boolean{
        return (txtNombreIngrediente.text.isNotEmpty() && txtCantidad.text.isNotEmpty())
    }

    private fun llenarSpinner(){
        val medidas = arrayOf<String>(
            "gr",
            "ml",
            "cda",
            "cdita",
            "tz",
            "pz"
        )
        val adapterMedidas = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, medidas)
        spinnerTiposMedidas.adapter = adapterMedidas
    }

    private fun llenarRecyclerIngredientes(){
        listaIngredientes.layoutManager = LinearLayoutManager(this)
        listaIngredientes.setHasFixedSize(false)
        listaIngredientes.adapter = IngredienteAdapter(ingredienteData)
    }
    private fun llenarRecyclerPasos(){
        listaPasos.layoutManager = LinearLayoutManager(this)
        listaPasos.setHasFixedSize(false)
        listaPasos.adapter = PasoAdapter(pasosData)
    }

    private fun limpiarCamposIngrediente(){
        txtNombreIngrediente.text.clear()
        txtCantidad.text.clear()
    }

    protected fun postAndNotifyAdapter(handler: Handler, lista: RecyclerView, index: Int){
        handler.post(Runnable {
            val holder = listaIngredientes.findViewHolderForLayoutPosition(index)
            if(holder!=null){
                val holderIngrediente = holder as IngredienteAdapter.IngredienteHolder
                val ingrediente = holderIngrediente.getIngrediente()
                ingrediente.idReceta = idReceta
                ingredientes.add(ingrediente)

                println(ingrediente.nombre)
            } else {
                postAndNotifyAdapter(handler, lista, index)
            }
        })
    }
}