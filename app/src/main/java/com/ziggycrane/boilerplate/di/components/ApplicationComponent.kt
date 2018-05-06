package com.ziggycrane.boilerplate.di.components

import android.content.Context
import android.os.Handler
import com.google.gson.Gson
import com.ziggycrane.boilerplate.BlueOrangeApplication
import com.ziggycrane.boilerplate.data.network.clients.AccountClient
import com.ziggycrane.boilerplate.data.preferences.BasePreferences
import com.ziggycrane.boilerplate.data.preferences.UserPreferences
import com.ziggycrane.boilerplate.di.AppContext
import com.ziggycrane.boilerplate.di.modules.activity.ActivityModule
import com.ziggycrane.boilerplate.di.modules.app.ApiModule
import com.ziggycrane.boilerplate.di.modules.app.ApplicationModule
import com.ziggycrane.boilerplate.di.qualifiers.BackgroundThread
import com.ziggycrane.boilerplate.di.qualifiers.MainThread
import com.ziggycrane.boilerplate.di.scopes.AppScope
import com.ziggycrane.boilerplate.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable

@AppScope
@Component(modules = [ApplicationModule::class, ApiModule::class, ActivityModule::class])
interface ApplicationComponent {

    fun inject(app: BlueOrangeApplication)

    @AppContext
    fun getContext(): Context

    fun application(): BlueOrangeApplication

    fun getCompositeDisposable(): CompositeDisposable
    fun getScheduleProvider(): SchedulerProvider

    fun getGson(): Gson

    fun getAccountClient(): AccountClient

    fun getBasePreferences(): BasePreferences
    fun getUserPreferences(): UserPreferences

    @BackgroundThread
    fun getBackgroundHandler(): Handler

    @MainThread
    fun getMainThreadHandler(): Handler
}