package clertonleal.com.githubloader.adapter.viewHolder

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

class DataBindingViewHolder<T : ViewDataBinding>(var binding: T) : RecyclerView.ViewHolder(binding.root)
