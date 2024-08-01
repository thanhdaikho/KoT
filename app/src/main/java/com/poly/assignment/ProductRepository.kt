package com.poly.assignment

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getProducts(@Url url: String): List<Product>

    @GET("product/{id}") // Ensure this matches your backend endpoint
    suspend fun getProductById(@Path("id") id: Int): Product
}

object ProductRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.5:4000/") // Ensure this is correct
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    suspend fun fetchProducts(url: String): List<Product> {
        return apiService.getProducts(url).map { product ->
            product.copy(images = product.images.map { it.replace("http://localhost:4000", "http://192.168.1.5:4000") })
        }
    }

    suspend fun getProductById(productId: Int): Product {
        return apiService.getProductById(productId)
    }
}