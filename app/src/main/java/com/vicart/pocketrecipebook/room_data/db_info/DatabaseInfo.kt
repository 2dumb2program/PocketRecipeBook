package com.vicart.pocketrecipebook.room_data.db_info

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vicart.pocketrecipebook.room_data.dao.IngredienteDAO
import com.vicart.pocketrecipebook.room_data.dao.RecetaDAO
import com.vicart.pocketrecipebook.room_data.entity.Ingrediente
import com.vicart.pocketrecipebook.room_data.entity.Receta

@Database (entities = [Receta::class, Ingrediente::class], version = 1)
abstract class DatabaseInfo: RoomDatabase() {
    abstract fun recetas(): RecetaDAO
    abstract fun ingredientes(): IngredienteDAO

    companion object{
        @Volatile
        private var INSTANCE: DatabaseInfo? = null

        fun getDatabase(context: Context): DatabaseInfo{
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, DatabaseInfo::class.java, "recetario_bd").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}