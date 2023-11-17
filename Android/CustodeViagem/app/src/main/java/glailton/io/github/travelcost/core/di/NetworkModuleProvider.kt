package glailton.io.github.travelcost.core.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object NetworkModuleProvider {
    private fun OkHttpClient.Builder.addLoggingInterceptor() {
        val loggingInterceptor = HttpLoggingInterceptor()

//        if (DeveloperUtils.enableDeveloperLogging) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
//        }

        addInterceptor(loggingInterceptor)
    }
}