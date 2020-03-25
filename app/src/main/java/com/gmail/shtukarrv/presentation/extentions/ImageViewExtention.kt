package com.gmail.shtukarrv.presentation.extentions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadFlagIcon(currencyCode: String?) {
    val iconResourcesId = context.resources
            .getIdentifier("ic_${currencyCode?.toLowerCase()}_flag", "drawable", context.packageName)

    Glide.with(context)
            .load(iconResourcesId)
            .centerCrop()
            .apply(RequestOptions.circleCropTransform())
            .into(this)
}