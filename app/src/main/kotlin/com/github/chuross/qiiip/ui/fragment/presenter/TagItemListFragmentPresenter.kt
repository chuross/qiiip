package com.github.chuross.qiiip.ui.fragment.presenter

import android.os.Bundle
import android.view.ViewGroup
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.fragment.TagItemListFragment
import com.github.chuross.qiiip.ui.fragment.template.ListFragmentTemplate
import rx.Observable

class TagItemListFragmentPresenter(fragment: TagItemListFragment) : PagerListFragmentPresenter<TagItemListFragment, ListFragmentTemplate, Item>(fragment) {

    val tag by lazy { view.arguments.getSerializable(TagItemListFragment.ARGUMENT_KEY_TAG) as Tag }
    private val itemRepository by lazy { view.application.itemRepository }

    override fun createTemplate(parent: ViewGroup?, savedInstance: Bundle?): ListFragmentTemplate = ListFragmentTemplate(view.activity)

    override fun request(page: Int, initialize: Boolean): Observable<List<Item>> = itemRepository.findAllByTagIdentity(tag.identity, page, perPage)

}