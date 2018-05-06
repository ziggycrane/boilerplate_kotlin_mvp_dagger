package com.ziggycrane.blueorange.ui.error

import com.ziggycrane.blueorange.di.scopes.ActivityScope
import com.ziggycrane.blueorange.ui.base.BaseContract

class ErrorDialogContract {

    interface View : BaseContract.BaseDialogView

    @ActivityScope
    interface Presenter<V : ErrorDialogContract.View> : BaseContract.BasePresenter<V>

}
