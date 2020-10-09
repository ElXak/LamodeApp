package com.ebo.lamode.ui.product

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ebo.lamode.R

private val TAB_TITLES = arrayOf(
    R.string.product,
    R.string.details,
    R.string.reviews
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class ProductPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a ProductFragment (defined as a static inner class below).

        when (position) {
            0 -> return ProductFragment.newInstance(position + 1)
            1 -> return ProductDetailsFragment.newInstance(position + 1)
            2 -> return ProductReviewsFragment.newInstance(position + 1)
            else -> return ProductFragment.newInstance(position + 1)
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 3
    }
}