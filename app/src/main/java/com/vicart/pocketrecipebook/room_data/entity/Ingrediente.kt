package com.vicart.pocketrecipebook.room_data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "Ingrediente")
class Ingrediente (val nombre: String,
                   var cantidadPorPersona: Double,
                   val tipoMedida: String,
                   var idReceta: Int = 0,
                   @PrimaryKey(autoGenerate = true) var idIngrediente: Int = 0): Serializable