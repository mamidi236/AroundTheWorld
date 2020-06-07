package com.raghav.aroundtheworld.fragments

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.raghav.aroundtheworld.AppConstants
import com.raghav.aroundtheworld.HomeViewModel
import com.raghav.aroundtheworld.R
import com.raghav.aroundtheworld.activities.CartActivity
import com.raghav.aroundtheworld.adapters.SingleCategoryAdapter
import com.raghav.aroundtheworld.data.SingleCategoryItem
import com.raghav.aroundtheworld.databinding.FragmentSingleCategoryBinding
import kotlinx.android.synthetic.main.custom_title_bar.*
import kotlinx.android.synthetic.main.custom_title_bar.view.*
import kotlinx.android.synthetic.main.fragment_single_category.*

class SingleCategoryFragment : Fragment() {

    private var mCategoryParam: String? = null

    lateinit var mHomeViewModel: HomeViewModel
    lateinit var mSingleCategoryBinding:FragmentSingleCategoryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mCategoryParam = it.getString(AppConstants.BUNDLE_CATEGORY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mSingleCategoryBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_single_category,container,false)
        return mSingleCategoryBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mHomeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        singlecategoryRv.adapter = SingleCategoryAdapter(getDisplayList(),requireContext(),object :SingleCategoryAdapter.recyclerItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(requireContext(),"Clicked at $position",Toast.LENGTH_LONG).show()
            }

        })

        setTitleBar()
        setClickListeners()
    }

    private fun setClickListeners() {
        ttBarLeftIv.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        ttBarRightIv.setOnClickListener {
            mHomeViewModel.showNavigationBar(Gravity.END)
        }
        cartIv.setOnClickListener {
            requireContext().startActivity(Intent(requireContext(),CartActivity::class.java))
        }
    }

    private fun setTitleBar() {
        ttBarLeftIv.setImageDrawable(resources.getDrawable(R.drawable.ic_back_arrow,requireContext().theme))
        ttBarRightIv.setImageDrawable(resources.getDrawable(R.drawable.ic_hamburger_24,requireContext().theme))
        cartIv.setImageDrawable(resources.getDrawable(R.drawable.ic_shopping_basket_24,requireContext().theme))
        cartIv.visibility = View.VISIBLE
    }

    fun getDisplayList(): List<SingleCategoryItem> {
         return when(mCategoryParam?.toInt()){
             11 -> {
                // display mens digital watches

                 listOf(
                    SingleCategoryItem(R.drawable.men_digital,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.men_digital,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.men_digital,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.men_digital,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.men_digital,"Watch one",1.2f,"$300")
                )
            }

            12 -> {
                // display mens analogue watches
                listOf(
                    SingleCategoryItem(R.drawable.men_analogue,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.men_analogue,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.men_analogue,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.men_analogue,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.men_analogue,"Watch one",1.2f,"$300")
                )
            }
            13 -> {
                // display mens chronograph watches
                listOf(
                    SingleCategoryItem(R.drawable.men_chromograph,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.men_chromograph,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.men_chromograph,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.men_chromograph,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.men_chromograph,"Watch one",1.2f,"$300")
                )
            }

            21 -> {
                // display womens digital watches
                listOf(
                    SingleCategoryItem(R.drawable.women_digital,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.women_digital,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.women_digital,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.women_digital,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.women_digital,"Watch one",1.2f,"$300")
                )
            }

            22 -> {
                // display womens analogue watches
                listOf(
                    SingleCategoryItem(R.drawable.women_analogue,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.women_analogue,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.women_analogue,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.women_analogue,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.women_analogue,"Watch one",1.2f,"$300")
                )
            }
            23 -> {
                // display womens chronograph watches
                listOf(
                    SingleCategoryItem(R.drawable.women_chromograph,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.women_chromograph,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.women_chromograph,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.women_chromograph,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.women_chromograph,"Watch one",1.2f,"$300")
                )
            }

            31 -> {
                listOf(
                    SingleCategoryItem(R.drawable.couple_watch_one,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.couple_watch_two,"Watch one",1.2f,"$300"),
                    SingleCategoryItem(R.drawable.couple_watch_three,"Watch one",1.2f,"$300")
                )
            }
            32 -> {
                // display couples analogue watches
                listOf()
            }
            33 -> {
                // display couples chronograph watches
                listOf()
            }
            else -> {
                listOf()

            }
        }
    }

}