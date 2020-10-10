package com.ebo.lamode

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ebo.lamode.ui.searchresult.SearchResultFragment
import com.ebo.lamode.ui.searchresult.SortPagerAdapter

class SearchResultActivity : AppCompatActivity(), SearchResultFragment.OnFragmentInteractionListener {

    private lateinit var viewPager: ViewPager
    private lateinit var sortPagerAdapter: SortPagerAdapter
    private lateinit var tabs: TabLayout

    companion object {
        val CATEGORY_ID = "CATEGORY_ID"
        //val VIEW_PARAMS = "VIEW_PARAMS"
        //val FILTER_PARAMS = "FILTER_PARAMS"
        lateinit var categoryId: String
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        categoryId = intent.getStringExtra(CATEGORY_ID)!!




        /*
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navDrawerView: NavigationView = findViewById(R.id.nav_drawer)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navDrawerView.setNavigationItemSelectedListener(this)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navView.itemIconTintList = null

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SearchResultFragment()).commit()
            //navDrawerView.setCheckedItem(R.id.nav_message)
        }
        */



        val sort: ArrayList<String> = arrayListOf("no", "rating", "priceAsc", "priceDesc")
        sortPagerAdapter = SortPagerAdapter(this, supportFragmentManager, sort)
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sortPagerAdapter
        tabs = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        viewPager.currentItem = sortPagerAdapter.count / 2


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
        menuInflater.inflate(R.menu.top_filter_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.top_filter -> true
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