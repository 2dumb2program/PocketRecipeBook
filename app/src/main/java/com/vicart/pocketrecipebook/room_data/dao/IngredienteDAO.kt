package com.vicart.pocketrecipebook.room_data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vicart.pocketrecipebook.room_data.entity.Ingrediente

@Dao
interface IngredienteDAO {
    @Query("SELECT * FROM Ingrediente")
    fun getAll(): LiveData<List<Ingrediente>>

    @Query("SELECT * FROM Ingrediente WHERE idReceta = :idReceta")
    fun getAllByReceta(idReceta: Int): LiveData<List<Ingrediente>>

    @Insert
    fun guardarIngrediente(vararg ingrediente: Ingrediente)

    @Update
    fun editarIngrediente(ingrediente: Ingrediente)
}