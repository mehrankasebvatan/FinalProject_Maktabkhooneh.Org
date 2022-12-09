package app.mkv.finalproject.api

import app.mkv.finalproject.model.Data
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("todos")
    fun getTodos(): Call<List<Data>>


}