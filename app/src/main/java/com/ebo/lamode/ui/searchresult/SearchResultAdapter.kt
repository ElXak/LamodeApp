package com.ebo.lamode.ui.searchresult

import com.ebo.lamode.R
import com.ebo.lamode.models.Product
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_search_result.view.*

class SearchResultAdapter(val product: Product): Item<ViewHolder>() {

    var productId: String = product.id

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(product.imageUrl).into(viewHolder.itemView.image_imageView)
        viewHolder.itemView.name_textView.text = product.name

        //val priceFormat = Resources.getSystem().getString(R.string.settings_currency)
        //Log.d("SearchResultAdapter", priceFormat)
        //viewHolder.itemView.price_textView.text = "$${product.price.toString()}"
        val priceFormat: String = "$%.2f"
        viewHolder.itemView.price_textView.text = String.format(priceFormat, product.price)
        //viewHolder.itemView.price_textView.text = String.format(Resources.getSystem().getString(R.string.settings_currency), product.price)

        viewHolder.itemView.rating_textView.text = product.rating.toString()
    }

    override fun getLayout(): Int {
        return R.layout.item_search_result
    }
}