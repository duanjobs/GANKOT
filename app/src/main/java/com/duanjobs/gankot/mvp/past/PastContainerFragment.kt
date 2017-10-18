package com.duanjobs.gankot.mvp.past

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.duanjobs.gankot.base.BaseFragment
import com.duanjobs.gankot.R
import com.duanjobs.gankot.adapter.BaseAdapter
import com.duanjobs.gankot.bean.History
import com.duanjobs.gankot.extensions.loading
import com.duanjobs.gankot.mvp.IndexContract
import com.duanjobs.gankot.utils.Const
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.item_history.view.*
import org.jetbrains.anko.support.v4.act

/**
 * Created by duanjobs on 17/8/29.
 */
class PastContainerFragment(override val layoutId: Int = R.layout.fragment_history) : BaseFragment()
        , IndexContract.PastView {

    var mPastPresenter: IndexContract.PastPresenter? = null
    var adapter: BaseAdapter<History>? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _title.text = "往期回顾"
        loading(Const.SHOW)
        mPastPresenter!!.getData()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun setPresenter(presenter: IndexContract.PastPresenter) {
        mPastPresenter = presenter
    }

    override fun setData(v: List<History>) {
        loading(Const.DISMISS)
        setUpView(v)
    }

    private fun setUpView(v: List<History>) {
        _recyclerView.layoutManager = LinearLayoutManager(act)
        adapter = BaseAdapter(R.layout.item_history, v, {
            view, history ->
            view.content.text = "${history!!.content}(${history!!.date})"
        }, {pos -> go2PastDetail(v[pos]) })
        _recyclerView.adapter = adapter
    }

    private fun go2PastDetail(history: History) {
        val intent = Intent(activity, PastDetailActivity::class.java)
        intent.putExtra("date", history.date)
        startActivity(intent)
    }

    companion object {
        fun getInstance(): PastContainerFragment = PastContainerFragment()
    }


}