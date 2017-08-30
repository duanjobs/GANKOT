package com.duanjobs.gankot.base

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.duanjobs.gankot.R
import kotlinx.android.synthetic.main.list_fragment_base_layout.*

/**
 * Created by duanjobs on 17/8/29.
 */
abstract class BaseListFragment(override val layoutId:Int = R.layout.list_fragment_base_layout):BaseFragment(){


    var pageNumber = 1
    var isFresh=true

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //初始化列表 并设置上拉下拉刷新
        initRecyleView()
        swipeLayout.setOnRefreshListener {
            pageNumber=1
            isFresh=true
            loadData(pageNumber,getType())
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView?.canScrollVertically(1)!!) {
                    isFresh=false
                    loadData(++pageNumber,getType())
                }
            }
        })
        loadData(pageNumber,getType())
    }

    abstract fun loadData(pageNumber: Int, type: String)

    abstract fun initRecyleView()

    abstract fun getType(): String
}