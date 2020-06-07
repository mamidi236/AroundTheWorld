package com.raghav.aroundtheworld.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.raghav.aroundtheworld.R
import com.raghav.aroundtheworld.databinding.ActivityCartBinding
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.custom_title_bar.*

class CartActivity : AppCompatActivity() {

    lateinit var mCartBinding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCartBinding = DataBindingUtil.setContentView(this,R.layout.activity_cart)
        setToolBar()
        setClickListener()
    }

    private fun setClickListener() {
        mCartBinding.placeOrderBt.setOnClickListener {
            it.visibility = View.GONE
            orderSucessTv.text = "Order Placed sucessfully...!"
            orderGroup.visibility = View.INVISIBLE
            orderSucessTv.visibility = View.VISIBLE
        }
        ttBarLeftIv.setOnClickListener {
            finish()
        }
    }

    private fun setToolBar() {
        ttBarLeftIv.setImageDrawable(getDrawable(R.drawable.ic_back_arrow))
        ttBarRightIv.visibility = View.GONE
        titleTv.text = "Bag"
    }
}