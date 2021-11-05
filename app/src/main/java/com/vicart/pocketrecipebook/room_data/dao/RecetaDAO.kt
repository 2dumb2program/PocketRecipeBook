package com.vicart.pocketrecipebook.room_data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vicart.pocketrecipebook.room_data.entity.Receta

@Dao
interface RecetaDAO {
    @Query("SELECT * FROM Receta WHERE categoria = :categoria AND estado = 'activo'")
    fun getRecetasPorCategoria(categoria: String): LiveData<List<Receta>>

    @Query ("SELECT MAX(idReceta) FROM Receta")
    fun getLastID(): Int

    @Query ("SELECT * FROM Receta WHERE idReceta = :idReceta")
    fun getRecetaPorID(idReceta: Int): LiveData<Receta>

    @Insert
    fun guardarReceta(vararg receta: Receta)

    @Update
    fun editarReceta(receta: Receta)
}