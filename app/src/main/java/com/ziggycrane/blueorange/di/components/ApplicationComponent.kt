package com.ziggycrane.blueorange.di.components

import android.content.Context
import android.os.Handler
import com.google.gson.Gson
import com.ziggycrane.blueorange.BlueOrangeApplication
import com.ziggycrane.blueorange.data.network.clients.AccountClient
import com.ziggycrane.blueorange.data.preferences.BasePreferences
import com.ziggycrane.blueorange.data.preferences.UserPreferences
import com.ziggycrane.blueorange.di.AppContext
import com.ziggycrane.blueorange.di.modules.ApiModule
import com.ziggycrane.blueorange.di.modules.ApplicationModule
import com.ziggycrane.blueorange.di.qualifiers.BackgroundThread
import com.ziggycrane.blueorange.di.qualifiers.MainThread
import com.ziggycrane.blueorange.di.scopes.AppScope
import com.ziggycrane.blueorange.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable

@AppScope
@Component(modules = [(ApplicationModule::class), (ApiModule::class)])
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