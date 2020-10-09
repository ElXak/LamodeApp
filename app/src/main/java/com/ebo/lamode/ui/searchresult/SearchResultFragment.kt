package com.ebo.lamode.ui.searchresult

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.ebo.lamode.ProductActivity
import com.ebo.lamode.R
import com.ebo.lamode.models.Product
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.content_search_result.*

/**
 * A placeholder fragment containing a simple view.
 */
class SearchResultFragment : Fragment() {

    //private lateinit var pageViewModel: ProductViewModel

    private lateinit var viewParams: MutableMap<String, String>
    private var categoryId = "1"
    private lateinit var filterParams: MutableMap<String, String>
    private lateinit var sortParams: MutableMap<String, String>
    private var listener: OnFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        pageViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
        */

        arguments?.let {
            viewParams = ARG_VIEW_PARAMS
            categoryId = it.getString(ARG_CATEGORY_ID)!!
            filterParams = ARG_FILTER_PARAMS
            sortParams = ARG_SORT_PARAMS
        }
    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val textView: TextView = root.findViewById(R.id.section_label)
        pageViewModel.text.observe(this, Observer<String> {
            textView.text = it
        })
        return root
        */

        val view = inflater.inflate(R.layout.fragment_search_result, container, false)
        val columnCount = viewParams.get("columnCount").toString().toInt()
        val orientation = viewParams.get("orientation").toString()

        val recyclerView: RecyclerView = view.findViewById(R.id.searchResult_recyclerView)

        // Set the adapter
        with(recyclerView) {
            layoutManager = when {
                columnCount <= 1 -> {
                    when {
                        orientation.equals("horizontal") -> LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                        else -> LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                    }
                }
                else -> GridLayoutManager(context, columnCount)
            }
        }

        // Constructing Categories Section
        val adapter = GroupAdapter<ViewHolder>()
        val requestQueue = Volley.newRequestQueue(activity)

        val sort = sortParams.getValue("setSort")


        val url = "http://lamode.tj/json/index.php?catid=$categoryId&sort=$sort"

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                for (x in 0 until response.length()) {
                    val id = response.getJSONObject(x).getString("id")
                    val name = response.getJSONObject(x).getString("name")
                    val text = response.getJSONObject(x).getString("text")
                    val imageUrl = response.getJSONObject(x).getString("imageUrl")
                    val detailImageUrl = response.getJSONObject(x).getString("detailImageUrl")
                    val price = response.getJSONObject(x).getString("price").toDouble()
                    val discountPrice = response.getJSONObject(x).getString("discount_price").toDouble()
                    val rating = response.getJSONObject(x).getString("rating").toDouble()

                    adapter.add(
                        SearchResultAdapter(
                            Product(id, name, text, imageUrl, detailImageUrl, price, discountPrice, rating)
                        )
                    )
                }

                adapter.setOnItemClickListener { item, view ->
                    val searchResult = item as SearchResultAdapter

                    val intent = Intent(activity, ProductActivity::class.java)
                    intent.putExtra(ProductActivity.PRODUCT_ID, searchResult.productId)
                    startActivity(intent)
                }

                searchResult_recyclerView.adapter = adapter
            },
            Response.ErrorListener { error ->
                Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
            })

        requestQueue.add(jsonArrayRequest)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        //private const val ARG_SECTION_NUMBER = "section_number"

        // TODO: Customize parameter argument names
        var ARG_VIEW_PARAMS: MutableMap<String, String> = mutableMapOf("orientation" to "vertical", "columnCount" to "2")
        const val ARG_CATEGORY_ID = "1"
        var ARG_FILTER_PARAMS: MutableMap<String, String> = mutableMapOf("setFilter" to "no")
        var ARG_SORT_PARAMS: MutableMap<String, String> = mutableMapOf("setSort" to "no")

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */

        /*
        @JvmStatic
        fun newInstance(sectionNumber: Int): SearchResultFragment {
            return SearchResultFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
        */

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(viewParams:MutableMap<String, String>, categoryId: String, filterParams: MutableMap<String, String>, sortParams: MutableMap<String, String>) =
            SearchResultFragment().apply {
                arguments = Bundle().apply {
                    ARG_VIEW_PARAMS = viewParams
                    putString(ARG_CATEGORY_ID, categoryId)
                    ARG_FILTER_PARAMS = filterParams
                    ARG_SORT_PARAMS = sortParams
                }
            }
    }

}