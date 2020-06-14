package com.shoohna.happytimes.networking.interfaces

import com.shoohna.happytimes.pojo.ups.MostPoplarResponse
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface ups {

    @POST("viewed/1.json?api-key=roHUHPLF7rG51farRowie1WpLF8J4oeA")
    suspend fun get_UPS(): Response<MostPoplarResponse>

}