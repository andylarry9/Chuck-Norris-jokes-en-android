package com.example.chucknorris
import retrofit2.Call
import retrofit2.http.GET
interface ChuckNorrisService {
    @GET("jokes/random")
    fun getRandomJoke(): Call<ChuckNorrisJok>
}