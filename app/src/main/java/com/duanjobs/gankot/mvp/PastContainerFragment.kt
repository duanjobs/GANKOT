package com.duanjobs.gankot.mvp

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import com.duanjobs.gankot.base.BaseFragment
import com.duanjobs.gankot.R
import com.duanjobs.gankot.adapter.BaseAdapter
import com.duanjobs.gankot.bean.History
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.item_history.view.*
import org.jetbrains.anko.support.v4.act

/**
 * Created by duanjobs on 17/8/29.
 */
class PastContainerFragment(override val layoutId: Int = R.layout.fragment_history) : BaseFragment()
        , IndexContract.PastView {

    var mPastPresenter: IndexContract.PastPresenter? = null
    var adapter:BaseAdapter<History>?=null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _title.text = "往期回顾"
    }

    override fun onResume() {
        super.onResume()
        mPastPresenter!!.getData()
    }

    override fun setPresenter(presenter: IndexContract.PastPresenter) {
        mPastPresenter = presenter
    }

    override fun setData(v: List<History>) {
        setUpView(v)
    }

    private fun setUpView(v: List<History>) {
        _recyclerView.layoutManager = LinearLayoutManager(act)
        adapter = BaseAdapter(R.layout.item_history,v){
            view, history ->
            view.content.text = "${history!!.content}(${history!!.date})"
        }
        _recyclerView.adapter = adapter
    }

    companion object {
        fun getInstance():PastContainerFragment = PastContainerFragment()
    }


}