package com.ziggycrane.blueorange.di.modules.app

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.ziggycrane.blueorange.BlueOrangeApplication
import com.ziggycrane.blueorange.data.preferences.BasePreferences
import com.ziggycrane.blueorange.data.preferences.UserPreferences
import com.ziggycrane.blueorange.di.AppContext
import com.ziggycrane.blueorange.di.qualifiers.BackgroundThread
import com.ziggycrane.blueorange.di.qualifiers.MainThread
import com.ziggycrane.blueorange.di.qualifiers.PreferencesBase
import com.ziggycrane.blueorange.di.qualifiers.PreferencesUser
import com.ziggycrane.blueorange.di.scopes.AppScope
import com.ziggycrane.blueorange.utils.Constants
import com.ziggycrane.blueorange.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @AppContext
    internal fun provideContext(): Context {
        return application
    }

    @Provides
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    internal fun provideBlueOrangeApplication(): BlueOrangeApplication {
        return application as BlueOrangeApplication
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    @PreferencesBase
    internal fun providePreferenceName(): String {
        return Constants.PREF_BASE
    }

    @Provides
    @PreferencesUser
    internal fun providePreferenceUserName(): String {
        return Constants.PREF_USER
    }

    @Provides
    @BackgroundThread
    internal fun provideBackgroundThreadHandler(): Handler {
        return Handler()
    }

    @Provides
    @MainThread
    internal fun provideMainThreadHandler(): Handler {
        return Handler(Looper.getMainLooper())
    }

    @Provides
    @AppScope
    internal fun provideUserPreferences(@AppContext context: Context, @PreferencesUser prefUserName: String, gson: Gson): UserPreferences {
        return UserPreferences(context, prefUserName, gson)
    }

    @Provides
    @AppScope
    internal fun provideBasePreferences(@AppContext context: Context, @PreferencesBase prefBaseName: String, gson: Gson): BasePreferences {
        return BasePreferences(context, prefBaseName, gson)
    }
}
