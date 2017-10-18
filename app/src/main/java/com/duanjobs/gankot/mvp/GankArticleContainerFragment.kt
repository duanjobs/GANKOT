package com.duanjobs.gankot.mvp

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View
import com.duanjobs.gankot.base.BaseFragment
import com.duanjobs.gankot.R
import com.duanjobs.gankot.bean.PublishedDate
import com.duanjobs.gankot.adapter.MainAdapter
import com.duanjobs.gankot.extensions.loading
import com.duanjobs.gankot.mvp.recommend.*
import com.duanjobs.gankot.utils.Const
import kotlinx.android.synthetic.main.gank_article_container_fragment_layout.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by duanjobs on 17/8/29.
 */
class GankArticleContainerFragment(override val layoutId: Int = R.layout.gank_article_container_fragment_layout)
    :BaseFragment(),IndexContract.GankView{

    var mPresenter: IndexContract.GankPresenter? = null
    var  published: String?=null

    override fun setPresenter(presenter: IndexContract.GankPresenter) {
        mPresenter = checkNotNull(presenter)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loading(Const.SHOW)
        mPresenter!!.getData()
    }

    override fun onResume() {
        super.onResume()
    }


    override fun setData(result: PublishedDate) {
        loading(Const.DISMISS)
        if (result.error || result.results == null || result.results.size == 0) {
            published = getCurrentDate()
            return
        }

        published = result.results[0].replace("-","/")
        setUpViews()

    }

    private fun setUpViews() {
        val fragments = ArrayList<Fragment>()
        var recommend = RecommendFragment.getInstance(published!!)
        var android = AndroidFragment.getInstance()
        var ios = IOSFragment.getInstance()
        var web = WebFragment.getInstance()
        var video = VideoFragment.getInstance()
        var tuozhan = TuoZhanFragment.getInstance()
        RecommendPresenter(recommend)
        ListPresenter(android)
        ListPresenter(ios)
        ListPresenter(web)
        ListPresenter(video)
        ListPresenter(tuozhan)
        fragments.add(recommend)
        fragments.add(android)
        fragments.add(ios)
        fragments.add(web)
        fragments.add(video)
        fragments.add(tuozhan)
        val titles = resources.getStringArray(R.array.title)
        viewPager.adapter = MainAdapter(fragments, titles, childFragmentManager)
        viewPager.offscreenPageLimit = 6

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.setCurrentItem(tab!!.position, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }


    companion object {
        fun getInstance(): GankArticleContainerFragment = GankArticleContainerFragment()
    }
    fun getCurrentDate(): String {
        val sdf: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
        return sdf.format(Date())
    }

}