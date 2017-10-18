package com.duanjobs.gankot.mvp.about

import com.duanjobs.gankot.base.BaseFragment
import com.duanjobs.gankot.R

/**
 * Created by duanjobs on 17/8/29.
 */
class AboutContainerFragment(override val layoutId: Int =R.layout.fragment_about) :BaseFragment() {
    companion object {
        fun getInstance(): AboutContainerFragment = AboutContainerFragment()
    }
}