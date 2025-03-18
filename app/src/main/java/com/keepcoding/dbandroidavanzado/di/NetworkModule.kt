package com.keepcoding.dbandroidavanzado.di

import android.util.Log
import com.keepcoding.dbandroidavanzado.data.Network.HerosApi
import com.keepcoding.dbandroidavanzado.data.Network.LoginApi
import com.keepcoding.dbandroidavanzado.data.Network.model.CredentialsProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BearerClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BasicAuthClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BearerRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BasicAuthRetrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @BearerClient
    fun provideBearerOkHttpClient(credentialsProvider: CredentialsProvider): OkHttpClient {
        val token = credentialsProvider.getToken()
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
               chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @BasicAuthClient
    fun provideBasicAuthOkHttpClient(credentialsProvider: CredentialsProvider): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()

                if (credentialsProvider.username.isNotEmpty() && credentialsProvider.password.isNotEmpty()) {
                    val base64Credentials = Credentials.basic(credentialsProvider.username, credentialsProvider.password)
                    requestBuilder.addHeader("Authorization", base64Credentials)
                }

                val response = chain.proceed(requestBuilder.build())

                val responseBodyString = response.body?.string() ?: "{}"
                credentialsProvider.setToken(responseBodyString.trim())

                Log.d("HTTP_RESPONSE", "Response: $responseBodyString")
                val newResponseBody = responseBodyString.toResponseBody(response.body?.contentType())

                response.newBuilder()
                    .body(newResponseBody)
                    .build()
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
            })
            .build()
    }

    @Singleton
    @Provides
    @BearerRetrofit
    fun provideBearerRetrofit(@BearerClient OkHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dragonball.keepcoding.education/")
            .client(OkHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    @BasicAuthRetrofit
    fun provideBasicRetrofit(@BasicAuthClient OkHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dragonball.keepcoding.education/")
            .client(OkHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }


    @Provides
    fun provideHerosApi(@BearerRetrofit retrofit: Retrofit): HerosApi {
        return retrofit.create(HerosApi::class.java)

    }

    @Provides
    fun provideLoginApi(@BasicAuthRetrofit retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)

    }
}