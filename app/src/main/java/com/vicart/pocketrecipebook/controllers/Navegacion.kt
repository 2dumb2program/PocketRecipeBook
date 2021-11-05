package com.vicart.pocketrecipebook.controllers

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vicart.pocketrecipebook.R
import kotlinx.android.synthetic.main.activity_navegacion.*

class Navegacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navegacion)

        btnBuscar.setOnClickListener {
            if (!txtBuscador.text.isEmpty()){
                webView.loadUrl("https://www.google.com/search?q=" +
                        "${txtBuscador.text.toString().replace(" ", "+")}")
            } else {
                Toast.makeText(this, "Escriba un platillo en el buscador", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegresarNavegacion.setOnClickListener {
            finish()
        }
    }
}
