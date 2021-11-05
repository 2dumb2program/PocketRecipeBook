package com.vicart.pocketrecipebook.adapters

import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vicart.pocketrecipebook.R
import com.vicart.pocketrecipebook.controllers.InfoReceta
import com.vicart.pocketrecipebook.room_data.entity.Receta

class RecetaAdapter (private val arrayRecetas: ArrayList<Receta>):
    RecyclerView.Adapter<RecetaAdapter.RecetaHolder>() {

    class RecetaHolder (val itemRecetas: View): RecyclerView.ViewHolder (itemRecetas) {
        val nombreReceta: TextView = itemRecetas.findViewById(R.id.lblReceta)

        fun recuperarReceta(receta: Receta){
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context.applicationContext, InfoReceta::class.java)
                intent.putExtra("receta", receta)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_receta, parent, false)
        return RecetaHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecetaHolder, position: Int) {
        val recetaActual = arrayRecetas[position]
        holder.nombreReceta.text = recetaActual.nombre
        holder.recuperarReceta(recetaActual)
    }

    override fun getItemCount(): Int {
        return arrayRecetas.size
    }
}