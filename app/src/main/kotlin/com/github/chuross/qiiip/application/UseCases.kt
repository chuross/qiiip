package com.github.chuross.qiiip.application

import android.content.Context
import com.github.chuross.qiiip.domain.item.ItemIdentity
import com.github.chuross.qiiip.domain.tag.TagIdentity
import com.github.chuross.qiiip.domain.user.UserIdentity
import com.github.chuross.qiiip.usecase.AuthorizeAccount
import com.github.chuross.qiiip.usecase.item.*

class UseCases(val context: Context) {

    private val application: Application get() = Application.from(context)
    private val component: QiiipComponent get() = application.component

    fun authorizeAccount(code: String) = AuthorizeAccount(code).also { component.inject(it) }

    fun getItems(page: Int, perPage: Int) = GetItems(page, perPage).also { component.inject(it) }

    fun getItemsByTagId(tagIdentity: TagIdentity, page: Int, perPage: Int) = GetItemsByTagId(tagIdentity, page, perPage).also { component.inject(it) }

    fun getUserItems(userIdentity: UserIdentity, page: Int, perPage: Int) = GetUserItems(userIdentity, page, perPage).also { component.inject(it) }

    fun getMyStockItems(page: Int, perPage: Int) = GetStokeItems(page, perPage).also { component.inject(it) }

    fun isStockItem(itemIdentity: ItemIdentity) = IsStockItem(itemIdentity).also { component.inject(it) }

    fun addStockItem(itemIdentity: ItemIdentity) = AddStockItem(itemIdentity).also { component.inject(it) }

    fun removeStockItem(itemIdentity: ItemIdentity) = RemoveStockItem(itemIdentity).also { component.inject(it) }

    fun getFeedItems(page: Int, perPage: Int) = GetFeedItems(page, perPage).also { component.inject(it) }
}