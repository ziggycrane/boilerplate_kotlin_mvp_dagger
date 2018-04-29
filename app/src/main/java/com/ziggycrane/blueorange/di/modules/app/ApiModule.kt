package com.ziggycrane.blueorange.di.modules.app

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ziggycrane.blueorange.data.network.clients.AccountClient
import com.ziggycrane.blueorange.di.scopes.AppScope

import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule// Constructor needs one parameter to instantiate.
(private var baseUrl: String) {

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @AppScope
    internal fun providesSharedPreferences(application: Application):
    // Application reference must come from ApplicationModule.class
            SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides
    @AppScope
    internal fun provideGson(): Gson {
        val builder = GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()

        return builder.create()
    }

    @Provides
    @AppScope
    internal fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        var builder = OkHttpClient.Builder()
        builder = builder.readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    @AppScope
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()
    }


    @Provides
    @AppScope
    internal fun provideAccountClient(retrofit: Retrofit): AccountClient {
        return retrofit.create(AccountClient::class.java)
    }
}