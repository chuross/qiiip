package com.github.chuross.qiiip.ui.fragment.screen

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.ViewGroup
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.qiiip.ui.fragment.BaseFragment
import com.github.chuross.qiiip.ui.fragment.adapter.FragmentTitlePagerAdapter
import com.github.chuross.qiiip.ui.fragment.presenter.HomeScreenFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.HomeScreenFragmentTemplate
import com.github.chuross.qiiip.ui.Screen as AppScreen

class HomeScreenFragment : BaseFragment<HomeScreenFragmentPresenter, HomeScreenFragmentTemplate>(), ScreenFragment {

    override val screen: Screen = com.github.chuross.qiiip.ui.Screen.HOME
    override val screenExitAction: ScreenExitAction = ScreenExitAction.HIDE
    override val screenIdentity: String = HomeScreenFragment::class.java.name

    override fun createPresenter(): HomeScreenFragmentPresenter = HomeScreenFragmentPresenter(this)

    override fun createTemplate(p0: ViewGroup?, p1: Bundle?): HomeScreenFragmentTemplate = HomeScreenFragmentTemplate(activity)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)

        val adapter = FragmentTitlePagerAdapter(childFragmentManager, presenter.pagerItems)

        template.toolbar.title = getString(R.string.app_name)

        val viewPager = template.viewPager
        viewPager.adapter = adapter

        val tabLayout = template.tabLayout
        tabLayout.setTabsFromPagerAdapter(adapter)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        tabLayout.setupWithViewPager(viewPager)
    }
}