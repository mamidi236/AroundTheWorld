package com.raghav.aroundtheworld.fragments

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.raghav.aroundtheworld.AppConstants
import com.raghav.aroundtheworld.HomeViewModel
import com.raghav.aroundtheworld.R
import com.raghav.aroundtheworld.activities.CartActivity
import com.raghav.aroundtheworld.adapters.CategoryAdapter
import com.raghav.aroundtheworld.data.CategoryItem
import com.raghav.aroundtheworld.databinding.FragmentCategoryBinding
import kotlinx.android.synthetic.main.custom_title_bar.*
import kotlinx.android.synthetic.main.fragment_category.*


class CategoryFragment : Fragment() {
    private var mCategoryId: String? = null

    lateinit var mHomeViewModel: HomeViewModel
    lateinit var mCategoryBinding:FragmentCategoryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mCategoryId = it.getString(AppConstants.BUNDLE_CATEGORY)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mCategoryBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_category,container,false)
        return mCategoryBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mHomeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        mCategoryBinding.categoryRecyclerView.adapter = CategoryAdapter(getDisplayList(),requireContext(),object :CategoryAdapter.recyclerItemClickListener{
            override fun onItemClick(position: Int) {
                categoryRecyclerView.findNavController().navigate(R.id.action_categoryFragment_to_singleCategoryFragment,Bundle().apply {
                    putString(AppConstants.BUNDLE_CATEGORY,"${mCategoryId}${position+1}")
                })
            }
        })

        setToolBar()

        setClickListeners()
    }

    private fun setToolBar() {
        ttBarLeftIv.setImageDrawable(resources.getDrawable(R.drawable.ic_back_arrow,requireContext().theme))
        ttBarRightIv.setImageDrawable(resources.getDrawable(R.drawable.ic_hamburger_24,requireContext().theme))
        cartIv.setImageDrawable(resources.getDrawable(R.drawable.ic_shopping_basket_24,requireContext().theme))
        cartIv.visibility = View.VISIBLE
    }

    private fun setClickListeners() {
        ttBarLeftIv.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        ttBarRightIv.setOnClickListener {
            mHomeViewModel.showNavigationBar(Gravity.END)
        }
        cartIv.setOnClickListener {
            requireContext().startActivity(Intent(requireContext(), CartActivity::class.java))
        }
    }

    fun getDisplayList():List<CategoryItem>{
        return when(mCategoryId?.toInt()){
            1 -> {
                listOf<CategoryItem>(
                    CategoryItem("Digital Watches",R.drawable.men_digital_category),
                    CategoryItem("Analogue Watches",R.drawable.men_analogue_category),
                    CategoryItem("Chronograph Watches",R.drawable.men_chromograph)
                )
            }
            2 -> {
                listOf<CategoryItem>(
                    CategoryItem("Digital Watches",R.drawable.women_digital_category),
                    CategoryItem("Analogue Watches",R.drawable.women_analogue_category),
                    CategoryItem("Chronograph Watches",R.drawable.women_chronograph_category)
                )
            }
            else -> {
                listOf<CategoryItem>(
                    CategoryItem("Digital Watches",R.drawable.couple_watches),
                    CategoryItem("Analogue Watches",R.drawable.couple_watches),
                    CategoryItem("Chronograph Watches",R.drawable.couple_watches)
                )
            }
        }
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            CategoryFragment()
    }
}