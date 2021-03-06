package com.github.chuross.qiiip.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.databinding.ListItemBinding
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.rx.RxItemAdapter
import io.reactivex.Flowable

class ItemAdapter(context: Context, source: Flowable<List<Item>>) : RxItemAdapter<Item, BindingViewHolder<ListItemBinding>>(context, source) {

    override fun getAdapterId(): Int = R.layout.list_item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ListItemBinding> {
        return BindingViewHolder(ListItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ListItemBinding>, position: Int) {
        holder.binding.item = get(position)
    }
}