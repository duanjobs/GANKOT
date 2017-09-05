package com.duanjobs.gankot.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

import com.duanjobs.gankot.R
import com.duanjobs.gankot.base.BaseActivity
import com.duanjobs.gankot.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity(override val layoutId: Int = R.layout.activity_main) : BaseActivity() {
    var gank: GankArticleContainerFragment? = null
    var meizi: MeiZhiContainerFragment? = null
    var past: PastContainerFragment? = null
    var about: AboutContainerFragment? = null
    var mMeiZhiPresenter: IndexContract.ListPresenter? = null
    var mGankPresenter: IndexContract.GankPresenter? = null
    var mpastPresenter: IndexContract.PastPresenter? = null
    var lastIndex = -1
    var lastFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_recommend -> {
                    changeTab(0)
                }
                R.id.action_girl -> {
                    changeTab(1)
                }
                R.id.action_history -> {
                    changeTab(2)
                }
                R.id.action_about -> {
                    changeTab(3)
                }
            }

            true
        }
//        var meizi = MeiZhiContainerFragment.getInstance()
//        mMeiZhiPresenter = ListPresenter(meizi)
//        if (savedInstanceState == null) {
//            initFragment(meizi)
//        }
        gank = GankArticleContainerFragment.getInstance()
        mGankPresenter = GankPresenter(gank!!)
        initFragment(gank!!)
        lastIndex = 0
        lastFragment = gank


    }

    private fun changeTab(position: Int) {
        if (lastIndex == position) {
            return
        }
        when (position) {
            0 -> {
//                gank = fragmentManager.findFragmentByTag(GankArticleContainerFragment::class.java.simpleName)
//                        as GankArticleContainerFragment?
                if (gank == null) {
                    gank = GankArticleContainerFragment.getInstance()
                }
                switchContent(lastFragment!!, gank!!)
                lastFragment = gank
            }
            1 -> {
                if (meizi == null) {
                    meizi = MeiZhiContainerFragment.getInstance()
                    mMeiZhiPresenter = ListPresenter(meizi!!)
                }
                switchContent(lastFragment!!, meizi!!)
                lastFragment = meizi
            }
            2 -> {
                if (past == null) {
                    past = PastContainerFragment.getInstance()
                    mpastPresenter = PastPresenter(past!!)
                }
                switchContent(lastFragment!!, past!!)
                lastFragment = past
            }
            3 -> {
                if (about == null) {
                    about = AboutContainerFragment.getInstance()
                }
                switchContent(lastFragment!!, about!!)
                lastFragment = about
            }


        }
        lastIndex = position

    }

    fun switchContent(from: Fragment, to: Fragment) {
        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        if (!to.isAdded) {
            ft.hide(from).add(R.id.container, to)
        } else {
            ft.hide(from).show(to) 
        }
        ft.commit()
    }

    private fun initFragment(to: BaseFragment) {
        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().add(R.id.container, to).commit()
    }
}

