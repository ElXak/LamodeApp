package com.ebo.lamode.ui.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ebo.lamode.*
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

/**
 * A placeholder fragment containing a simple view.
 */
class ProductDetailsFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_product_details, container, false)

        /*
        val textView: TextView = root.findViewById(R.id.select_color_textView)

        val navView: BottomNavigationView = root.findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navView.itemIconTintList = null

        productViewModel.text.observe(this, Observer<String> {
            textView.text = it
        })
        */
        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ProductDetailsFragment {
            return ProductDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}