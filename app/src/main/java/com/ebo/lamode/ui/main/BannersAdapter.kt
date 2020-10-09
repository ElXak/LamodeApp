package com.ebo.lamode.ui.main

import com.ebo.lamode.R
import com.ebo.lamode.models.Product
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_banners.view.background_imageView
import kotlinx.android.synthetic.main.item_banners.view.text_textView

class BannersAdapter(val product: Product): Item<ViewHolder>() {
    var productId: String = product.id

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(product.detailImageUrl)
            .resizeDimen(R.dimen.banners_item_image_width, R.dimen.banners_item_image_height).centerCrop()
            .into(viewHolder.itemView.background_imageView)
        viewHolder.itemView.text_textView.text = product.text
    }

    override fun getLayout(): Int {
        return R.layout.item_banners
    }
}