package edu.quinnipiac.ser210.myapplication
/*
  * Gabby Pierce and Sam Woodburn
  * Final Project SER210
  * Gym Genie
  * Main activity
 */

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting the Toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Gym Genie"

        //navigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        //app bar nav
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        //fix navigate back, this isn't working
        val frag = navController.currentDestination as? HomeFragment
        frag?.resetSpinner()

        // Handle normal up navigation
        return navController.navigateUp() || super.onSupportNavigateUp()
    }




    //toolbar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        // Configure ShareActionProvider
        val shareItem = menu?.findItem(R.id.action_share)
        val shareActionProvider = androidx.core.view.MenuItemCompat.getActionProvider(shareItem!!) as? androidx.appcompat.widget.ShareActionProvider

        // Create an Intent to share your content
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "This is a message for you.")
        }

        // Attach the share intent to the ShareActionProvider
        shareActionProvider?.setShareIntent(shareIntent)

        return true
    }

    //handle option menu clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when(id){
            R.id.action_info -> {
                val navController = findNavController(R.id.nav_host_fragment)
                val action = when (navController.currentDestination?.id) {
                    R.id.HomeFragment -> HomeFragmentDirections.actionHomeFragmentToInformationFragment()
                    R.id.AllWorkoutsFragment -> AllWorkoutsFragmentDirections.actionAllWorkoutsFragmentToInformationFragment()
                    R.id.SavedWorkoutsFragment -> SavedWorkoutsFragmentDirections.actionSavedWorkoutsFragmentToInformationFragment()
                    else -> null
                }
                action?.let { navController.navigate(it) }
                return true
            }
            R.id.action_paint -> {
                showSettingsMenu(findViewById(item.itemId))
                true
            }
            R.id.action_share -> {
                true
            }
            else -> NavigationUI.onNavDestinationSelected(item!!, navController)
                    ||super.onOptionsItemSelected(item)
        }
    }


    //settings menu
    private fun showSettingsMenu(anchor: View) {
        val popup = PopupMenu(this, anchor)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.paintmenu, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            // Handle submenu item clicks
            when (menuItem.itemId) {
                R.id.blue_setting -> {
                    changeBackgroundColor(R.color.blue)
                    true
                }
                R.id.pink_setting -> {
                    changeBackgroundColor(R.color.pink)
                    true
                }
                R.id.green_setting -> {
                    changeBackgroundColor(R.color.green)
                    true
                }
                R.id.purple_setting -> {
                    changeBackgroundColor(R.color.purple)
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
    fun changeBackgroundColor(color: Int) {
        val layout = findViewById<FrameLayout>(R.id.main_layout)
        layout.setBackgroundColor(ContextCompat.getColor(this, color))
    }

}
