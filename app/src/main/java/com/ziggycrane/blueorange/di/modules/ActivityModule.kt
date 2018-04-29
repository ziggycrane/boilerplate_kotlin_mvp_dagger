package com.ziggycrane.blueorange.di.modules

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.ziggycrane.blueorange.di.ActivityContext
import com.ziggycrane.blueorange.di.scopes.ActivityScope
import com.ziggycrane.blueorange.ui.auth.*
import com.ziggycrane.blueorange.ui.base.BaseActivity
import com.ziggycrane.blueorange.ui.dashboard.DashboardPresenter
import com.ziggycrane.blueorange.ui.error.ErrorDialogContract
import com.ziggycrane.blueorange.ui.error.ErrorDialogPresenter
import com.ziggycrane.blueorange.ui.splash.SplashPresenter

import dagger.Module
import dagger.Provides

@Module
class ActivityModule(@get:Provides
                     private val activity: BaseActivity) {

    val context: Context
        @Provides
        @ActivityContext
        get() = activity

    @Provides
    @ActivityScope
    internal fun provideSplashPresenter(
            presenter: SplashPresenter<SplashContract.View>): SplashContract.Presenter<SplashContract.View> {
        return presenter
    }

    @Provides
    @ActivityScope
    internal fun provideAuthPresenter(
            presenter: AuthPresenter<AuthContract.View>): AuthContract.Presenter<AuthContract.View> {
        return presenter
    }

    @Provides
    @ActivityScope
    internal fun provideDashboardPresenter(
            presenter: DashboardPresenter<DashboardContract.View>): DashboardContract.Presenter<DashboardContract.View> {
        return presenter
    }

    @Provides
    internal fun provideErrorDialogPresenter(
            presenter: ErrorDialogPresenter<ErrorDialogContract.View>): ErrorDialogContract.Presenter<ErrorDialogContract.View> {
        return presenter
    }

//    @Provides
//    internal fun provideSubTasksAdapter(activity: BaseActivity): TaskStagesAdapter {
//        return TaskStagesAdapter(activity, ArrayList<TaskStage>())
//    }

    @Provides
    internal fun provideLinearLayoutManager(activity: BaseActivity): LinearLayoutManager {
        return LinearLayoutManager(activity)
    }
}
