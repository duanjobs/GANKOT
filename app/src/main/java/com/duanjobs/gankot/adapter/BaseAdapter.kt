package com.duanjobs.gankot.adapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.duanjobs.gankot.extensions.ctx
import org.jetbrains.anko.layoutInflater

/**
 *Created by duanjobs on 17/8/29.
 */
class BaseAdapter<T>(val layoutResourceId: Int, var items: List<T>, val init: (View, T) -> Unit) :
        RecyclerView.Adapter<BaseAdapter.ViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val view = parent.ctx.layoutInflater.inflate(layoutResourceId, parent, false)
        //这个也可以 不用anko
        //val view = LayoutInflater.from(parent.ctx).inflate(layoutResourceId, parent, false)
        return ViewHolder(view, init)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder<in T>(view: View, val init: (View, T) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: T) {
            with(item) {
                init(itemView, item)
            }
        }
    }
}

