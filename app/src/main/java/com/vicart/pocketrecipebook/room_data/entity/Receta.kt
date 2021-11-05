package com.vicart.pocketrecipebook.room_data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "Receta")
class Receta (val nombre: String,
              val preparacion: String,
              val categoria: String,
              var estado: String = "activo",
              @PrimaryKey(autoGenerate = true) var idReceta: Int = 0) : Serializable