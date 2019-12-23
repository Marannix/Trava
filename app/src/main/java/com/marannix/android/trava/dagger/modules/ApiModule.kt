package com.marannix.android.trava.dagger.modules

import com.marannix.android.trava.api.HeaderInterceptor
import com.marannix.android.trava.api.VenueApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://api.foursquare.com/v2/"

/**
 * Api Module for networking
 */

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        // Debugging purpose to show request and response information
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideVenueApi(retrofit: Retrofit): VenueApi {
        return retrofit.create(VenueApi::class.java)
    }
}