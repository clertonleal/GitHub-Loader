package clertonleal.com.githubloader.adapter

import clertonleal.com.githubloader.R
import clertonleal.com.githubloader.adapter.viewHolder.DataBindingViewHolder
import clertonleal.com.githubloader.databinding.RepositoryRowBinding
import clertonleal.com.githubloader.model.Repository
import clertonleal.com.githubloader.viewModel.RepositoryViewModel

class RepositoryAdapter : BaseDataBindingAdapter<Repository, RepositoryRowBinding>() {

    override val layout: Int
        get() = R.layout.repository_row

    override fun onBindViewHolder(holder: DataBindingViewHolder<RepositoryRowBinding>, position: Int) {
        super.onBindViewHolder(holder, position)
        val repository = list[position]
        holder.binding.viewModel = RepositoryViewModel(repository)
        holder.binding.container.setOnClickListener { onClick?.invoke(list[position]) }
    }

}
