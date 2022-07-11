package com.example.main
import com.example.example.Model
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface getdata {

    @GET("top-headlines")
    fun getnews( @Query("country") country : String , @Query("apiKey")
    apiKey :String): Call<Model>


}