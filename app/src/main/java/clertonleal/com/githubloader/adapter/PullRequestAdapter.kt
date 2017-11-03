package clertonleal.com.githubloader.adapter

import clertonleal.com.githubloader.R
import clertonleal.com.githubloader.adapter.viewHolder.DataBindingViewHolder
import clertonleal.com.githubloader.databinding.PullRequestRowBinding
import clertonleal.com.githubloader.model.PullRequest
import clertonleal.com.githubloader.viewModel.PullRequestViewModel

class PullRequestAdapter : BaseDataBindingAdapter<PullRequest, PullRequestRowBinding>() {

    override val layout: Int
        get() = R.layout.pull_request_row

    override fun onBindViewHolder(holder: DataBindingViewHolder<PullRequestRowBinding>, position: Int) {
        val pullRequest = list[position]
        holder.binding.viewModel = PullRequestViewModel(pullRequest)
        holder.binding.container.setOnClickListener { onClick?.invoke(pullRequest) }
    }

}
