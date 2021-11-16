package com.example.starter.ui

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.starter.R
import com.example.starter.base.BaseActivity
import com.example.starter.databinding.ActivityMainBinding
import com.example.starter.ui.navigation.setupWithNavController
import com.example.starter.util.BackButtonCloseHandler


class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private var currentNavController: LiveData<NavController>? = null

    private val backButtonCloseHandler = BackButtonCloseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onBackPressed() {
        when (currentNavController?.value?.currentDestination?.label) {
            getString(R.string.title_home) -> backButtonCloseHandler.onBackPressed()
            else -> super.onBackPressed()
        }
    }
    private fun setUpBottomNavigationBar() {

        val navGraphIds = listOf(R.navigation.program, R.navigation.youtube, R.navigation.mall)
        val controller = dataBinding.navView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment_activity_main,
            intent = intent
        )

        currentNavController = controller
    }

}