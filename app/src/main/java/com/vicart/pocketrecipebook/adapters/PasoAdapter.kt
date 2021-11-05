package com.vicart.pocketrecipebook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vicart.pocketrecipebook.R

class PasoAdapter (private val arrayPasos: ArrayList<Paso>):
    RecyclerView.Adapter<PasoAdapter.PasoHolder>(){

    class PasoHolder (val itemPaso: View): RecyclerView.ViewHolder (itemPaso) {
        val numPaso: TextView = itemPaso.findViewById(R.id.lblNumeroPaso)
        val paso: TextView = itemPaso.findViewById(R.id.lblPaso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasoHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_paso, parent, false)
        return PasoHolder(itemView)
    }

    override fun onBindViewHolder(holder: PasoHolder, position: Int) {
        val pasoActual = arrayPasos[position]
        holder.numPaso.text = pasoActual.numeroPaso.toString()
        holder.paso.text = pasoActual.paso
    }

    override fun getItemCount(): Int {
        return arrayPasos.size
    }
}