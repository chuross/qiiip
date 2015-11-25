package com.github.chuross.qiiip.ui.fragment.common

import android.app.Activity
import android.os.Bundle
import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter
import com.github.chuross.library.mvp.view.fragment.SupportPresentationFragment
import com.github.chuross.library.mvp.view.template.Template
import com.github.chuross.qiiip.ui.activity.ScreenActivity
import com.trello.rxlifecycle.FragmentEvent
import com.trello.rxlifecycle.RxLifecycle
import com.trello.rxlifecycle.components.FragmentLifecycleProvider
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.subjects.BehaviorSubject

/**
 * @see
 * https://github.com/trello/RxLifecycle/blob/master/rxlifecycle-components/src/main/java/com/trello/rxlifecycle/components/support/RxFragment.java
 */
abstract class BaseFragment<PRESENTER : SupportFragmentPresenter<*, TEMPLATE>, TEMPLATE : Template> : SupportPresentationFragment<PRESENTER, TEMPLATE>(), FragmentLifecycleProvider {

    val screenActivity by lazy { activity as ScreenActivity }

    private val lifecycle = BehaviorSubject.create<FragmentEvent>()

    override fun lifecycle(): Observable<FragmentEvent>? = lifecycle.asObservable()

    override fun <T : Any?> bindUntilEvent(event: FragmentEvent?): Observable.Transformer<T, T>? = RxLifecycle.bindUntilFragmentEvent(lifecycle, event)

    override fun <T : Any?> bindToLifecycle(): Observable.Transformer<T, T>? = RxLifecycle.bindFragment(lifecycle)

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        lifecycle.onNext(FragmentEvent.ATTACH)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.onNext(FragmentEvent.CREATE)
    }

    override fun onViewCreated(template: TEMPLATE, savedInstanceState: Bundle?) {
        super.onViewCreated(template, savedInstanceState)
        lifecycle.onNext(FragmentEvent.CREATE_VIEW)
    }

    override fun onStart() {
        super.onStart()
        lifecycle.onNext(FragmentEvent.START)
    }

    override fun onResume() {
        super.onResume()
        lifecycle.onNext(FragmentEvent.RESUME)
    }

    override fun onPause() {
        lifecycle.onNext(FragmentEvent.PAUSE)
        super.onPause()
    }

    override fun onStop() {
        lifecycle.onNext(FragmentEvent.STOP)
        super.onStop()
    }

    override fun onDestroyView() {
        lifecycle.onNext(FragmentEvent.DESTROY_VIEW)
        super.onDestroyView()
    }

    override fun onDestroy() {
        lifecycle.onNext(FragmentEvent.DESTROY)
        super.onDestroy()
    }

    override fun onDetach() {
        lifecycle.onNext(FragmentEvent.DETACH)
        super.onDetach()
    }

    fun <R> processObservable(scheduler: Scheduler, observable: Observable<R>): Observable<R> {
        return observable.compose(bindToLifecycle<R>())
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
    }
}