package com.ebo.lamode.ui.product

import com.smarteist.autoimageslider.SliderViewAdapter
import com.ebo.lamode.R
import android.content.Context
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_banners.view.*


class SliderAdapter(private val context: Context) : SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate = LayoutInflater.from(context).inflate(R.layout.item_slider, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        viewHolder.textViewDescription.text = "This is slider item $position"

        when (position) {
            0 -> Picasso.get().load("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                .into(viewHolder.imageViewBackground)
                /*
                Glide.with(viewHolder.itemView)
                .load("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                .into(viewHolder.imageViewBackground)
                */
            1 -> Picasso.get().load("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260")
                .into(viewHolder.imageViewBackground)
            2 -> Picasso.get().load("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                .into(viewHolder.imageViewBackground)
            else -> Picasso.get().load("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                .into(viewHolder.imageViewBackground)
        }

    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return 4
    }

    inner class SliderAdapterVH(var itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        var imageViewBackground: ImageView
        var textViewDescription: TextView

        init {
            imageViewBackground = itemView.findViewById(R.id.image_imageView)
            textViewDescription = itemView.findViewById(R.id.text_textView)
        }
    }
}