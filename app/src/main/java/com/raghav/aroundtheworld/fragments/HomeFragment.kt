package com.raghav.aroundtheworld.fragments

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.raghav.aroundtheworld.AppConstants
import com.raghav.aroundtheworld.HomeViewModel
import com.raghav.aroundtheworld.R
import com.raghav.aroundtheworld.activities.CartActivity
import com.raghav.aroundtheworld.adapters.HomeAdapter
import com.raghav.aroundtheworld.data.HomeItem
import com.raghav.aroundtheworld.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.custom_title_bar.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var mHomeViewModel: HomeViewModel
    lateinit var mHomeFragmentBinding: FragmentHomeBinding


    var list = listOf<HomeItem>(
        HomeItem(R.drawable.men_watches,"Men Watches"),
        HomeItem(R.drawable.women_watches,"Women watches"),
        HomeItem(R.drawable.couple_watches,"Couple Watches"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mHomeFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        return mHomeFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mHomeFragmentBinding.homeRecyclerView.adapter = HomeAdapter(list,requireContext(),object : HomeAdapter.recyclerItemClickListener{
            override fun onItemClick(position: Int) {
                when(position){
                    2 ->{
                        homeRecyclerView.findNavController().navigate(R.id.action_homeFragment_to_singleCategoryFragment,Bundle().apply {
                            putString(AppConstants.BUNDLE_CATEGORY,"31")
                        })
                    }
                    else ->{
                        homeRecyclerView.findNavController().navigate(R.id.action_homeFragment_to_categoryFragment,Bundle().apply {
                            putString(AppConstants.BUNDLE_CATEGORY,"${position+1}")
                        })
                    }
                }
            }
        })

        setClickListeners()
    }

    private fun setClickListeners() {
        ttBarLeftIv.setOnClickListener {
            mHomeViewModel.showNavigationBar(Gravity.START)
        }
        ttBarRightIv.setOnClickListener {
            requireContext().startActivity(Intent(requireContext(), CartActivity::class.java))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = HomeFragment()
    }
}
