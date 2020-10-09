package com.ebo.lamode.ui.main

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.ebo.lamode.models.Category
import com.ebo.lamode.models.Product
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.content_home.*
import android.content.Intent
import android.view.LayoutInflater
import com.ebo.lamode.*

class HomeFragment : Fragment() {
    /*
    private var messagesRedCircle: FrameLayout? = null
    private var messagesCountTextView: TextView? = null
    private var messagesAlertCount = 0

    private var notificationsRedCircle: FrameLayout? = null
    private var notificationsCountTextView: TextView? = null
    private var notificationsAlertCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Constructing Categories Section
        val categoriesAdapter = GroupAdapter<ViewHolder>()
        val requestQueue = Volley.newRequestQueue(activity)

        var url = "http://lamode.tj/json/categories.php"

        val categoriesJsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                for (x in 0 until response.length()) {
                    val id = response.getJSONObject(x).getString("id")
                    val name = response.getJSONObject(x).getString("name")
                    val imageUrl = response.getJSONObject(x).getString("imageUrl")

                    categoriesAdapter.add(
                        CategoriesAdapter(
                            Category(id, name, imageUrl), false, context!!
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
                    val intent = Intent(activity, CategoriesActivity::class.java)
                    intent.putExtra(CategoriesActivity.CATEGORY_ID, category.categoryId)
                    startActivity(intent)
                    //finish()
                }

                categories_recyclerView.adapter = categoriesAdapter
            },
            Response.ErrorListener { error ->
                Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
            })

        requestQueue.add(categoriesJsonArrayRequest)

        // Constructing Banners Section
        val bannersAdapter = GroupAdapter<ViewHolder>()

        /*
        url = "http://lamode.tj/json/banners.php"

        val bannersJsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                for (x in 0 until response.length()) {
                    val id = response.getJSONObject(x).getString("id")
                    val name = response.getJSONObject(x).getString("name")
                    val text = response.getJSONObject(x).getString("text")
                    val imageUrl = response.getJSONObject(x).getString("imageUrl")
                    val price = response.getJSONObject(x).getString("price")
                    val rating = response.getJSONObject(x).getString("rating")

                    bannersAdapter.add(
                        BannersAdapter(
                            Banner(id, name, text, imageUrl, price, rating)
                        )
                    )
                }

                bannersAdapter.setOnItemClickListener { item, view ->
                    val banner = item as BannersAdapter

                    val intent = Intent(activity, ProductActivity::class.java)
                    intent.putExtra(ProductActivity.PRODUCT_ID, banner.id)
                    startActivity(intent)
                }

                banners_recyclerView.adapter = bannersAdapter
            },
            Response.ErrorListener { error ->
                Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
            })

        requestQueue.add(bannersJsonArrayRequest)
        */

        // Constructing Products Section
        val productsAdapter = GroupAdapter<ViewHolder>()

        url = "http://lamode.tj/json/index.php"

        val productsJsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
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

                    when (x) {
                        in 0..2 -> {
                            bannersAdapter.add(
                                BannersAdapter(
                                    Product(id, name, text, imageUrl, detailImageUrl, price, discountPrice, rating)
                                )
                            )
                        }
                        else -> {
                            productsAdapter.add(
                                ProductsAdapter(
                                    Product(id, name, text, imageUrl, detailImageUrl, price, discountPrice, rating)
                                )
                            )
                        }
                    }

                }

                bannersAdapter.setOnItemClickListener { item, view ->
                    val banner = item as BannersAdapter

                    val intent = Intent(activity, ProductActivity::class.java)
                    intent.putExtra(ProductActivity.PRODUCT_ID, banner.productId)
                    startActivity(intent)
                }

                banners_recyclerView.adapter = bannersAdapter

                productsAdapter.setOnItemClickListener { item, view ->
                    val product = item as ProductsAdapter

                    val intent = Intent(activity, ProductActivity::class.java)
                    intent.putExtra(ProductActivity.PRODUCT_ID, product.productId)
                    startActivity(intent)
                }

                products_recyclerView.adapter = productsAdapter
            },
            Response.ErrorListener { error ->
                Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
            })

        requestQueue.add(productsJsonArrayRequest)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    /*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_top, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        val messagesMenuItem = menu?.findItem(R.id.top_messages)
        val messagesRootView = messagesMenuItem?.actionView as FrameLayout
        messagesRedCircle = messagesRootView.findViewById<View>(R.id.view_message_red_circle) as FrameLayout
        messagesCountTextView = messagesRootView.findViewById(R.id.view_message_count_textview) as TextView
        messagesCountTextView?.text = "3"
        messagesRedCircle?.visibility = VISIBLE
        messagesRootView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                onOptionsItemSelected(messagesMenuItem)
            }
        })

        val notificationsMenuItem = menu.findItem(R.id.top_notifications)
        val notificationsRootView = notificationsMenuItem?.actionView as FrameLayout
        notificationsRedCircle = notificationsRootView.findViewById<View>(R.id.view_notification_red_circle) as FrameLayout
        notificationsCountTextView = notificationsRootView.findViewById(R.id.view_notification_count_textview) as TextView
        notificationsCountTextView?.text = "4"
        notificationsRedCircle?.visibility = VISIBLE
        notificationsRootView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                onOptionsItemSelected(notificationsMenuItem)
            }
        })

        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
        */
        val fragmentTransaction: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()

        when (item.itemId) {
            R.id.top_messages -> {
                /*
                messagesAlertCount = (messagesAlertCount + 1) % 11 // cycle through 0 - 10
                updateMessagesIcon()
                */

                fragmentTransaction.replace(
                    R.id.fragment_container,
                    MessagesFragment()
                ).addToBackStack(null).commit()

                //textMessage.setText(R.string.top_messages)
                return true
            }
            R.id.top_notifications -> {
                fragmentTransaction.replace(
                    R.id.fragment_container,
                    NotificationsFragment()
                ).addToBackStack(null).commit()

                //textMessage.setText(R.string.top_notifications)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateMessagesIcon() {
        // if alert count extends into two digits, just show the red circle
        if (0 < messagesAlertCount && messagesAlertCount < 10) {
            messagesCountTextView?.text = messagesAlertCount.toString()
        } else {
            messagesCountTextView?.text = ""
        }

        messagesRedCircle?.setVisibility(if (messagesAlertCount > 0) VISIBLE else GONE)
    }
    */
}