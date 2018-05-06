package com.ziggycrane.boilerplate.ui.error

import com.ziggycrane.boilerplate.di.scopes.ActivityScope
import com.ziggycrane.boilerplate.ui.base.BaseContract

class ErrorDialogContract {

    interface View : BaseContract.BaseDialogView

    @ActivityScope
    interface Presenter<V : ErrorDialogContract.View> : BaseContract.BasePresenter<V>

}
