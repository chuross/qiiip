package com.github.chuross.qiiip.usecase.item

import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.item.ItemRepository
import com.github.chuross.qiiip.domain.user.UserIdentity
import com.github.chuross.qiiip.usecase.BaseRxUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetUserItems(val userIdentity: UserIdentity, val page: Int, val perPage: Int) :BaseRxUseCase<List<Item>>() {

    @Inject lateinit var application: Application
    @Inject lateinit var itemRepository: ItemRepository

    override fun source(): Single<List<Item>> {
        return itemRepository.findAllByUserIdentity(userIdentity, page, perPage)
    }
}