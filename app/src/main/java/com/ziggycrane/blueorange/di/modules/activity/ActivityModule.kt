package com.ziggycrane.blueorange.di.modules.activity

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.ziggycrane.blueorange.data.preferences.BasePreferences
import com.ziggycrane.blueorange.data.preferences.UserPreferences
import com.ziggycrane.blueorange.di.ActivityContext
import com.ziggycrane.blueorange.di.scopes.ActivityScope
import com.ziggycrane.blueorange.ui.auth.*
import com.ziggycrane.blueorange.ui.base.BaseActivity
import com.ziggycrane.blueorange.ui.dashboard.DashboardContract
import com.ziggycrane.blueorange.ui.dashboard.DashboardPresenter
import com.ziggycrane.blueorange.ui.error.ErrorDialogContract
import com.ziggycrane.blueorange.ui.error.ErrorDialogPresenter
import com.ziggycrane.blueorange.ui.splash.SplashContract
import com.ziggycrane.blueorange.ui.splash.SplashPresenter
import com.ziggycrane.blueorange.utils.rx.SchedulerProvider

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
    fun provideSplashPresenter(@ActivityContext context: Context,
                               userPreferences: UserPreferences,
                               schedulerProvider: SchedulerProvider,
                               compositeDisposable: CompositeDisposable): SplashPresenter<SplashContract.View> {
        return SplashPresenter(context, userPreferences, schedulerProvider, compositeDisposable)
    }

    @Provides
    @ActivityScope
    fun provideAuthPresenter(@ActivityContext context: Context,
                             userPreferences: UserPreferences,
                             schedulerProvider: SchedulerProvider,
                             compositeDisposable: CompositeDisposable): AuthPresenter<AuthContract.View> {
        return AuthPresenter(context, userPreferences, schedulerProvider, compositeDisposable)
    }

    @Provides
    @ActivityScope
    fun provideDashboardPresenter(@ActivityContext context: Context,
                                  basePreferences: BasePreferences,
                                  userPreferences: UserPreferences,
                                  schedulerProvider: SchedulerProvider,
                                  compositeDisposable: CompositeDisposable): DashboardPresenter<DashboardContract.View> {
        return DashboardPresenter(context, basePreferences, userPreferences, schedulerProvider, compositeDisposable)
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
