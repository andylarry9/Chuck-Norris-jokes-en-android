package com.example.chucknorris

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar el click listener para el botón de refresco
        val buttonRefresh = findViewById<Button>(R.id.buttonRefresh)
        buttonRefresh.setOnClickListener {
            Log.d("MainActivity", "Botón de refresco clickeado") // Verifica si el botón se clickea correctamente
            // Hacer una solicitud para obtener una nueva broma aleatoria de Chuck Norris
            RetrofitClient.chuckNorrisService.getRandomJoke().enqueue(object : Callback<ChuckNorrisJok> {
                override fun onResponse(call: Call<ChuckNorrisJok>, response: Response<ChuckNorrisJok>) {
                    if (response.isSuccessful) {
                        val joke = response.body()
                        joke?.let {
                            // Actualizar el TextView con la nueva broma
                            findViewById<TextView>(R.id.textViewJoke).text = it.value
                        }
                    } else {
                        Log.e("MainActivity", "Error en la respuesta de la API: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<ChuckNorrisJok>, t: Throwable) {
                    Log.e("MainActivity", "Error al realizar la llamada a la API", t)
                }
            })
        }
    }
}

