package com.example.co.mealapp.Api

import com.example.co.mealapp.Utils.Constans
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AppMudel {

    fun builder()=Retrofit.Builder()
        .baseUrl(Constans.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()!!

        private fun provideLoggingInterceptor():Interceptor{
            return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
    private fun provideOkHttp():OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(10,TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .addInterceptor(provideLoggingInterceptor())
            .build()
    }
}