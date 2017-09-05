package com.duanjobs.gankot.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import java.util.*

/**
 *
 */
class MainAdapter(var data:List<Fragment> = ArrayList(), var titles :Array<String>,fm:FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return data[position]
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}