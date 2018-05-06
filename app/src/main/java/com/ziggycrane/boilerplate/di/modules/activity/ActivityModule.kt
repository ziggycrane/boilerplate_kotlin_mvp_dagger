package com.ziggycrane.boilerplate.di.modules.activity

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.ziggycrane.boilerplate.data.preferences.BasePreferences
import com.ziggycrane.boilerplate.data.preferences.UserPreferences
import com.ziggycrane.boilerplate.di.ActivityContext
import com.ziggycrane.boilerplate.di.scopes.ActivityScope
import com.ziggycrane.boilerplate.ui.auth.*
import com.ziggycrane.boilerplate.ui.base.BaseActivity
import com.ziggycrane.boilerplate.ui.dashboard.DashboardContract
import com.ziggycrane.boilerplate.ui.dashboard.DashboardPresenter
import com.ziggycrane.boilerplate.ui.error.ErrorDialogContract
import com.ziggycrane.boilerplate.ui.error.ErrorDialogPresenter
import com.ziggycrane.boilerplate.ui.splash.SplashContract
import com.ziggycrane.boilerplate.ui.splash.SplashPresenter
import com.ziggycrane.boilerplate.utils.rx.SchedulerProvider

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity) {

    @Provides
    fun getActivity(): BaseActivity {
        return activity
    }

    @Provides
    @ActivityContext
    fun getContext(): Context {
        return activity
    }

    @Provides
    @ActivityScope
    fun provideSplashPresenter(presenter: SplashPresenter<SplashContract.View>): SplashContract.Presenter<SplashContract.View> {
        return presenter
    }

    @Provides
    @ActivityScope
    fun provideAuthPresenter(presenter: AuthPresenter<AuthContract.View>): AuthContract.Presenter<AuthContract.View> {
        return presenter
    }

    @Provides
    @ActivityScope
    fun provideDashboardPresenter(presenter: DashboardPresenter<DashboardContract.View>): DashboardContract.Presenter<DashboardContract.View> {
        return presenter
    }

    @Provides
    @ActivityScope
    fun provideErrorDialogPresenter(
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
