package com.github.chuross.qiiip.ui.fragment.common

import android.support.v4.app.Fragment
import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter
import com.github.chuross.library.mvp.view.template.Template
import rx.Observable

abstract class RequestFragmentPresenter<FRAGMENT : Fragment, TEMPLATE : Template, R>(fragment: FRAGMENT) : SupportFragmentPresenter<FRAGMENT, TEMPLATE>(fragment) {

    abstract fun getRequestOptions(): RequestOptions

    fun request(): Observable<R> = Observable.empty()

}