package clertonleal.com.githubloader.databinding

import android.databinding.BindingAdapter
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.widget.ImageView

import com.squareup.picasso.Picasso

import clertonleal.com.githubloader.R

object DataBindingUtils {

    @JvmStatic
    @BindingAdapter("url")
    fun setImageDrawable(view: ImageView, imageUrl: String) {
        Picasso.with(view.context).load(imageUrl).error(R.drawable.profile_placeholder).into(view)
    }

    @JvmStatic
    @BindingAdapter("divider")
    fun setImageDrawable(recyclerView: RecyclerView, orientation: Int) {
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

}
