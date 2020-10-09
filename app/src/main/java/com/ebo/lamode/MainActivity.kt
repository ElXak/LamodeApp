package com.ebo.lamode

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.ebo.lamode.ui.main.HomeFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener/*, SearchResultFragment.OnFragmentInteractionListener*/ {
    private lateinit var messagesRedCircle: FrameLayout
    private lateinit var messagesCountTextView: TextView
    private var messagesAlertCount = 0

    private lateinit var notificationsRedCircle: FrameLayout
    private lateinit var notificationsCountTextView: TextView
    private var notificationsAlertCount = 0

    //private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    HomeFragment()
                ).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    SearchFragment()
                ).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_cart -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    CartFragment()
                ).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    ProfileFragment()
                ).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_more -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    MoreFragment()
                ).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navDrawerView: NavigationView = findViewById(R.id.nav_drawer)
        navDrawerView.setNavigationItemSelectedListener(this)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navView.itemIconTintList = null

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                HomeFragment()
            ).commit()
            //navDrawerView.setCheckedItem(R.id.nav_message)
        }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
                Toast.makeText(this, "Camera", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_gallery -> {
                Toast.makeText(this, "Gallery", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_slideshow -> {
                Toast.makeText(this, "Slide show", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_tools -> {
                Toast.makeText(this, "Tools", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_share -> {
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_send -> {
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.top_notifications, menu)
        //return true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val messagesMenuItem = menu?.findItem(R.id.top_messages)
        val messagesRootView = messagesMenuItem?.actionView as FrameLayout
        messagesRedCircle = messagesRootView.findViewById<View>(R.id.view_message_red_circle) as FrameLayout
        messagesCountTextView = messagesRootView.findViewById(R.id.view_message_count_textview) as TextView
        messagesCountTextView.text = "3"
        if (messagesCountTextView.text != "")
            messagesRedCircle.visibility = View.VISIBLE
        messagesRootView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                onOptionsItemSelected(messagesMenuItem)
            }
        })

        val notificationsMenuItem = menu.findItem(R.id.top_notifications)
        val notificationsRootView = notificationsMenuItem?.actionView as FrameLayout
        notificationsRedCircle = notificationsRootView.findViewById<View>(R.id.view_notification_red_circle) as FrameLayout
        notificationsCountTextView = notificationsRootView.findViewById(R.id.view_notification_count_textview) as TextView
        notificationsCountTextView.text = "4"
        if (notificationsCountTextView.text != "")
            notificationsRedCircle.visibility = View.VISIBLE
        notificationsRootView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                onOptionsItemSelected(notificationsMenuItem)
            }
        })

        return super.onPrepareOptionsMenu(menu)
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
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()

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
            messagesCountTextView.text = messagesAlertCount.toString()
        } else {
            messagesCountTextView.text = ""
        }

        messagesRedCircle.setVisibility(if (messagesAlertCount > 0) View.VISIBLE else View.GONE)
    }

    /*
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    */
}
