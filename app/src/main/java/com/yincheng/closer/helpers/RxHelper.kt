package com.yincheng.closer.helpers

import androidx.annotation.NonNull
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxHelper {
    fun <T> getObservable(@NonNull observable: Observable<T>): Observable<T> =
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}