package com.raghav.aroundtheworld.activities

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.raghav.aroundtheworld.HomeViewModel
import com.raghav.aroundtheworld.LoginActivity
import com.raghav.aroundtheworld.R
import com.raghav.aroundtheworld.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    lateinit var mHomeViewModel: HomeViewModel
    lateinit var mHomeActivityBinding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomeActivityBinding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        mHomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        observeHamberger()
        setClickListener()
    }

    fun setClickListener(){
        nav_view.setNavigationItemSelectedListener { menuItem: MenuItem ->
            when(menuItem.itemId){
                R.id.account ->{
                    startActivity(Intent(this,CartActivity::class.java))
                }
                R.id.settings ->{

                }
                R.id.about -> {

                }
            }

            true
        }
        logout.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java).apply {
                this.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

            })
            finish()
        }
    }

    private fun observeHamberger() {
        mHomeViewModel.navigationDrawer.observe(this, Observer {gravity ->
            when(gravity){
                Gravity.END -> {
                    val layoutParams = DrawerLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT)
                    layoutParams.gravity = Gravity.END
                    nav_view.layoutParams = layoutParams
                    mHomeActivityBinding.drawerLayout.openDrawer(gravity)
                }
                else -> {
                    val layoutParams = DrawerLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT)
                    layoutParams.gravity = Gravity.START
                    nav_view.layoutParams = layoutParams
                    mHomeActivityBinding.drawerLayout.openDrawer(gravity)
                }
            }
        })
    }
}
