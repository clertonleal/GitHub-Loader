package clertonleal.com.githubloader.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import java.util.ArrayList

import clertonleal.com.githubloader.adapter.viewHolder.DataBindingViewHolder

abstract class BaseDataBindingAdapter<M, B : ViewDataBinding> : RecyclerView.Adapter<DataBindingViewHolder<B>>() {

    var list: MutableList<M> = ArrayList()
    var onClick: ((M) -> Unit)? = null
    var onLoadMore: ((Int) -> Unit)? = null
    private var page = 1

    protected abstract val layout: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<B> {
        val binding = DataBindingUtil.inflate<B>(LayoutInflater.from(parent.context), layout, parent, false)
        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<B>, position: Int) {
        if (!list.isEmpty() && position == list.size - 1) {
            page += 1
            onLoadMore?.invoke(page)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addList(list: MutableList<M>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun initialList(list: MutableList<M>) {
        this.list = list
        notifyDataSetChanged()
    }
}
