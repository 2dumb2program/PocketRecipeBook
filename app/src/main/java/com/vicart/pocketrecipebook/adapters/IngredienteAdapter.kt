package com.vicart.pocketrecipebook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vicart.pocketrecipebook.R
import com.vicart.pocketrecipebook.room_data.entity.Ingrediente

class IngredienteAdapter(private val arrayIngredientes: ArrayList<Ingrediente>):
    RecyclerView.Adapter<IngredienteAdapter.IngredienteHolder>() {

    class IngredienteHolder (val itemIngrediente: View): RecyclerView.ViewHolder (itemIngrediente){
        val nombreIngrediente: TextView = itemIngrediente.findViewById(R.id.lblNombreIngrediente)
        val cantidad: TextView = itemIngrediente.findViewById(R.id.lblCantidad)
        val tipoMedida: TextView = itemIngrediente.findViewById(R.id.lblTipoMedida)

        fun getIngrediente(): Ingrediente{
            return Ingrediente(nombreIngrediente.text.toString(), cantidad.text.toString().toDouble(), tipoMedida.text.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredienteHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_ingrediente, parent, false)
        return IngredienteHolder(itemView)
    }

    override fun onBindViewHolder(holder: IngredienteHolder, position: Int) {
        val ingredienteActual = arrayIngredientes[position]
        holder.nombreIngrediente.text = ingredienteActual.nombre
        holder.cantidad.text = ingredienteActual.cantidadPorPersona.toString()
        holder.tipoMedida.text = ingredienteActual.tipoMedida
    }

    override fun getItemCount(): Int {
        return arrayIngredientes.size
    }
}