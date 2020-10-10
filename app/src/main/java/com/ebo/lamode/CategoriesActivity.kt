package com.ebo.lamode

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.ebo.lamode.models.Category
import com.ebo.lamode.ui.main.CategoriesAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.content_home.*

class CategoriesActivity : AppCompatActivity() {

    companion object {
        val CATEGORY_ID = "CATEGORY_ID"
        //val VIEW_PARAMS = "VIEW_PARAMS"
        //val FILTER_PARAMS = "FILTER_PARAMS"
        lateinit var categoryId: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        setSupportActionBar(toolbar)

        categoryId = intent.getStringExtra(CATEGORY_ID)!!

        /*
        fab.setOnClickListener { view ->
            finish()
            /*
            Snackbar.make(view, "Category ID: $categoryId", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
             */
        }
        */
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Constructing Categories Section
        val categoriesAdapter = GroupAdapter<ViewHolder>()
        val requestQueue = Volley.newRequestQueue(this)

        val url = "http://lamode.tj/json/categories.php"

        val categoriesJsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                for (x in 0 until response.length()) {
                    val id = response.getJSONObject(x).getString("id")
                    val name = response.getJSONObject(x).getString("name")
                    val imageUrl = response.getJSONObject(x).getString("imageUrl")

                    categoriesAdapter.add(
                        CategoriesAdapter(
                            Category(id, name, imageUrl), true, this
                        )
                    )
                }

                categoriesAdapter.setOnItemClickListener { item, view ->
                    val category = item as CategoriesAdapter
                    /*
                    val viewParams: MutableMap<String, String>? = mutableMapOf("columnCount" to "2", "orientation" to "vertical")
                    val filterParams: MutableMap<String, String>? = mutableMapOf("setFilter" to "no")
                    val sortParams: MutableMap<String, String>? = mutableMapOf("setSort" to "no")
                    */

                    /*
                    if (savedInstanceState == null) {
                        val fragmentTransaction: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
                        var viewParams: MutableMap<String, String>? = mutableMapOf("columnCount" to "2", "orientation" to "vertical")
                        var filterParams: MutableMap<String, String>? = mutableMapOf("setFilter" to "no")

                        fragmentTransaction.replace(
                            R.id.fragment_container,
                            SearchResultFragment1.newInstance(viewParams, category.categoryId, filterParams)
                        ).addToBackStack(null).commit()
                    }
                    */

                    //Toast.makeText(activity, category.categoryId, Toast.LENGTH_SHORT).show()
                    /* Intent to SearchResultActivity
                    val intent = Intent(activity, SearchResultActivity::class.java)
                    intent.putExtra(SearchResultActivity.CATEGORY_ID, category.categoryId)
                     */
                    val intent = Intent(this, CategoriesActivity::class.java)
                    intent.putExtra(CATEGORY_ID, category.categoryId)
                    startActivity(intent)
                    //finish()
                }

                categories_recyclerView.adapter = categoriesAdapter
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            })

        requestQueue.add(categoriesJsonArrayRequest)

    }
}
