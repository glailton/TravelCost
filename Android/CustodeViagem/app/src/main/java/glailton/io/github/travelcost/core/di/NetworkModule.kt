package glailton.io.github.travelcost.core.di

import glailton.io.github.travelcost.core.data.remote.TravelCostApi
import glailton.io.github.travelcost.utils.ApiEndpoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "http://10.0.2.2:5242/travelcost/"

fun provideHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        addLoggingInterceptor()
    }.build()
}

private fun OkHttpClient.Builder.addLoggingInterceptor() {
    val loggingInterceptor = HttpLoggingInterceptor()

    if (BuildConfig.DEBUG) {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    }

    addInterceptor(loggingInterceptor)
}

fun provideRetrofit(apiEndpoint: ApiEndpoint, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit
        .Builder()
        .baseUrl(apiEndpoint.baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideTravelCostApi(retrofit: Retrofit): TravelCostApi {
    return retrofit.create(TravelCostApi::class.java)
}