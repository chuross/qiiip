package com.github.chuross.qiiip.ui.fragment.presenter

import android.support.v4.app.Fragment
import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter
import com.github.chuross.library.mvp.view.template.Template

class EmptyFragmentPresenter<F : Fragment, T : Template>(fragment: F) : SupportFragmentPresenter<F, T>(fragment) {

}