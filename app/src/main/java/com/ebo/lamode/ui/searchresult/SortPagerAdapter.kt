package com.ebo.lamode.ui.searchresult

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ebo.lamode.R
import com.ebo.lamode.SearchResultActivity

private val TAB_TITLES = arrayOf(
    R.string.best_match,
    R.string.top_rated,
    R.string.price_lowHigh,
    R.string.price_highLow
)

private const val MAX_VALUE = 200

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */

class SortPagerAdapter(private val context: Context, fm: FragmentManager, private val sort: ArrayList<String>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a SearchResultFragment (defined as a static inner class below).
        //return SearchResultFragment.newInstance(position + 1)
        val viewParams: MutableMap<String, String> = mutableMapOf("columnCount" to "2", "orientation" to "vertical")
        val filterParams: MutableMap<String, String> = mutableMapOf("setFilter" to "no")
        val sortParams: MutableMap<String, String> = mutableMapOf("setSort" to sort[position % sort.size])

        /*
        Log.d("SortPagerAdapter", position.toString())
        Log.d("SortPagerAdapter", sortParams["setSort"])
        */


        /*
        when (position) {
            0 -> sortParams = mutableMapOf("setSort" to "no")
            1 -> sortParams = mutableMapOf("setSort" to "rating")
            2 -> sortParams = mutableMapOf("setSort" to "priceAsc")
            3 -> sortParams = mutableMapOf("setSort" to "priceDesc")
        }
        */

        return SearchResultFragment.newInstance(viewParams, SearchResultActivity.categoryId, filterParams, sortParams)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position % sort.size])
    }

    override fun getCount(): Int {
        // Show 4 total pages.
        return sort.size * MAX_VALUE
    }
}