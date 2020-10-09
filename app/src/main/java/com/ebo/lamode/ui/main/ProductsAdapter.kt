package com.ebo.lamode.ui.main

import com.ebo.lamode.R
import com.ebo.lamode.models.Product
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_search_result.view.*

class ProductsAdapter(val product: Product): Item<ViewHolder>() {

    var productId: String = product.id

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(product.imageUrl)
            .resizeDimen(R.dimen.products_item_image_maxHeight, R.dimen.products_item_image_maxHeight).centerCrop()
            .into(viewHolder.itemView.image_imageView)

        viewHolder.itemView.name_textView.text = product.name

        val priceFormat: String = "$%.2f"
        viewHolder.itemView.price_textView.text = String.format(priceFormat, product.price)

        viewHolder.itemView.rating_textView.text = product.rating.toString()
    }

    override fun getLayout(): Int {
        return R.layout.item_products
    }
}