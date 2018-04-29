package com.ziggycrane.blueorange.di.components

import com.ziggycrane.blueorange.ui.auth.AuthActivity
import com.ziggycrane.blueorange.ui.splash.SplashActivity
import com.ziggycrane.blueorange.data.preferences.BasePreferences
import com.ziggycrane.blueorange.di.modules.ActivityModule
import com.ziggycrane.blueorange.di.scopes.ActivityScope
import com.ziggycrane.blueorange.ui.dashboard.DashboardActivity
import com.ziggycrane.blueorange.ui.error.ErrorDialogFragment
import dagger.Component

@ActivityScope
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun basePreferences(): BasePreferences

    fun inject(activity: SplashActivity)
    fun inject(activity: AuthActivity)
    fun inject(activity: DashboardActivity)
    fun inject(fragment: ErrorDialogFragment)
}
