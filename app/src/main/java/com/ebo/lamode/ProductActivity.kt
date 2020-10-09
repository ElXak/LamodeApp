package com.ebo.lamode

import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.ebo.lamode.ui.product.ProductPagerAdapter
import com.ebo.lamode.ui.product.SliderAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.smarteist.autoimageslider.SliderView
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.IndicatorAnimations

class ProductActivity : AppCompatActivity() {
    private lateinit var cartRedCircle: FrameLayout
    private lateinit var cartCountTextView: TextView
    private var cartAlertCount = 0

    companion object {
        val PRODUCT_ID = "PRODUCT_ID"
        var productId: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        productId = intent.getStringExtra(PRODUCT_ID)

        Log.d("ProductActivity", productId!!)

        val sliderView: SliderView = findViewById(R.id.imageSlider)

        val adapter = SliderAdapter(this)

        sliderView.sliderAdapter = adapter
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        /*
        sliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        sliderView.indicatorSelectedColor = Color.WHITE
        sliderView.indicatorUnselectedColor = Color.GRAY
        sliderView.scrollTimeInSec = 4
        */
        sliderView.startAutoCycle()

        val productTabsAdapter = ProductPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = productTabsAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        /*
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        */
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.top_cart_menu, menu)

        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val cartMenuItem = menu?.findItem(R.id.top_cart)
        val cartRootView = cartMenuItem?.actionView as FrameLayout
        cartRedCircle = cartRootView.findViewById<View>(R.id.view_cart_red_circle) as FrameLayout
        cartCountTextView = cartRootView.findViewById(R.id.view_cart_count_textview) as TextView
        cartCountTextView.text = "3"
        cartRedCircle.visibility = View.VISIBLE
        cartRootView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                onOptionsItemSelected(cartMenuItem)
            }
        })

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.top_cart -> true
            else -> super.onOptionsItemSelected(item)
        }

        /*
        when (item.itemId) {
            R.id.top_messages -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MessagesFragment()).addToBackStack(null).commit()
                //textMessage.setText(R.string.top_messages)
                return true
            }
            R.id.top_notifications -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, NotificationsFragment()).addToBackStack(null).commit()
                //textMessage.setText(R.string.top_notifications)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
        */
    }
}