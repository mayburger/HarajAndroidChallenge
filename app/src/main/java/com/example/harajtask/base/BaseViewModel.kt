package com.example.harajtask.base

import androidx.lifecycle.ViewModel
import com.example.harajtask.data.AppDataManager
import java.lang.ref.WeakReference


abstract class BaseViewModel<N: BaseNavigator>(
    val dataManager: AppDataManager
) : ViewModel() {

    private val TAG = "BaseViewModel"

    private lateinit var mNavigator: WeakReference<N?>
    var navigator: N?
        get() = mNavigator.get()
        set(navigator) {
            this.mNavigator = WeakReference(navigator)
        }
}
