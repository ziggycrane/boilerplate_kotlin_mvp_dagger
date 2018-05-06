package com.ziggycrane.boilerplate.di.components

import com.ziggycrane.boilerplate.ui.splash.SplashActivity
import com.ziggycrane.boilerplate.di.modules.activity.ActivityModule
import com.ziggycrane.boilerplate.di.scopes.ActivityScope
import com.ziggycrane.boilerplate.ui.auth.AuthActivity
import com.ziggycrane.boilerplate.ui.dashboard.DashboardActivity
import com.ziggycrane.boilerplate.ui.error.ErrorDialogFragment
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: AuthActivity)
    fun inject(activity: DashboardActivity)
    fun inject(fragment: ErrorDialogFragment)
    fun inject(activity: SplashActivity)
}
