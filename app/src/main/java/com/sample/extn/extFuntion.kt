package com.sample.extn

import android.content.Context
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sample.BuildConfig
import com.sample.R


fun ImageView.loadImage(url: String) = Glide.with(this)
    .load(BuildConfig.IMAGE_URL + url)
    .placeholder(R.drawable.placeholder_image_24).into(this)


fun extractName(name: String) = name.substringBefore("-").trim()

fun textFromHtml(name: String): Spanned = Html.fromHtml(name, Html.FROM_HTML_MODE_LEGACY)

fun Context.showToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()


fun View.hide() {
    this.visibility = View.GONE
}


fun View.show() {
    this.visibility = View.VISIBLE
}




