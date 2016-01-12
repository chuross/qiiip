package com.github.chuross.qiiip.ui.fragment

import android.os.Bundle
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.qiiip.ui.fragment.presenter.PagerListFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.ListTemplate
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView

abstract class PagerListFragment<P : PagerListFragmentPresenter<*, RESULT>, T : ListTemplate, RESULT> : ListFragment<P, T, RESULT>() {

    override fun onRequestSuccess(result: List<RESULT>, initialize: Boolean) {
        super.onRequestSuccess(result, initialize)

        template.swipeRefreshLayout.isRefreshing = false
    }

    override fun onRequestFailed(error: Throwable, initialize: Boolean) {
        super.onRequestFailed(error, initialize)

        template.swipeRefreshLayout.isRefreshing = false
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)

        template.swipeRefreshLayout.setColorSchemeResources(R.color.primary)
        template.swipeRefreshLayout.setOnRefreshListener {
            request(true)
        }

        subscriptions.add(RxRecyclerView.scrollEvents(template.list)
                .filter { event -> !adapter.isEmpty() && !event.view().canScrollVertically(1) }
                .subscribe({
                    request(false)
                }))
    }

}