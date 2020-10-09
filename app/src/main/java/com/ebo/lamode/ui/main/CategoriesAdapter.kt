package com.ebo.lamode.ui.main

import android.content.Context
import androidx.core.content.ContextCompat
import com.ebo.lamode.CategoriesActivity
import com.ebo.lamode.R
import com.ebo.lamode.models.Category
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_categories.view.*

class CategoriesAdapter(private val category: Category, private val selected: Boolean = false, val context: Context): Item<ViewHolder>() {
    var categoryId: String = category.id

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.name_textView.text = category.name
        Picasso.get().load(category.imageUrl).into(viewHolder.itemView.image_imageView)

        if (selected) {
            if (category.id == CategoriesActivity.categoryId)
                viewHolder.itemView.background_linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorSecondary));
            else
                viewHolder.itemView.background_linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_categories
    }
}