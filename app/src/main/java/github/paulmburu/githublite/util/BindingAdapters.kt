package github.paulmburu.githublite.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import github.paulmburu.githublite.R

@BindingAdapter("imageDrawable")
fun bindImage(imgView: ImageView, imageUrl: String) {
    imageUrl.let {
        Glide.with(imgView.context)
            .load(it)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}