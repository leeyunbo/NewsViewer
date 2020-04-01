package com.leeyunbo.myrealtrip.adapters

import androidx.recyclerview.widget.DiffUtil
import com.leeyunbo.myrealtrip.data.News

class RecyclerDiffCallback(private var oldItems : ArrayList<News>,
                           private var newItems : ArrayList<News>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        var oldItem = oldItems.get(oldItemPosition)
        var newItem = newItems.get(newItemPosition)
        return oldItem.equals(newItem)
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        var oldItem = oldItems.get(oldItemPosition)
        var newItem = newItems.get(newItemPosition)

        if(!oldItem.title.equals(newItem.title)) return false
        if(!oldItem.description.equals(newItem.description)) return false

        return true
    }
}