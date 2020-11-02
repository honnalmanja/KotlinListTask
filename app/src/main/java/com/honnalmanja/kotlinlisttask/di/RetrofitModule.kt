package com.honnalmanja.kotlinlisttask.di

import com.honnalmanja.kotlinlisttask.data.api.TaskServiceAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule(private val BASE_URL: String) {

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        val bodyInterceptor = HttpLoggingInterceptor()
        val headerInterceptor = HttpLoggingInterceptor()
        bodyInterceptor.level = HttpLoggingInterceptor.Level.BODY
        headerInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        return OkHttpClient().newBuilder()
                .addInterceptor(bodyInterceptor)
                .addInterceptor(headerInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideRemoteService(retrofit: Retrofit): TaskServiceAPI {
        return retrofit.create(TaskServiceAPI::class.java)
    }
}