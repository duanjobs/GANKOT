package com.duanjobs.gankot.mvp

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.SimpleItemAnimator
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.duanjobs.gankot.R
import com.duanjobs.gankot.adapter.BaseAdapter
import com.duanjobs.gankot.base.BaseListFragment
import com.duanjobs.gankot.bean.GankArticle
import com.duanjobs.gankot.bean.Type
import kotlinx.android.synthetic.main.item_girl.view.*
import kotlinx.android.synthetic.main.list_fragment_base_layout.*
import org.jetbrains.anko.support.v4.act

/**
 * Created by duanjobs on 17/8/29.
 */
class MeiZhiContainerFragment : BaseListFragment() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.visibility = View.VISIBLE
        main_title.text = "妹纸福利"
    }

    override fun initRecyleView() {
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        var simpleAnimator: SimpleItemAnimator = recyclerView.itemAnimator as SimpleItemAnimator
        simpleAnimator.supportsChangeAnimations = false
        adapter = BaseAdapter(R.layout.item_girl, mlist, { view: View, gankArticle: GankArticle ->
            Glide.with(act).load(gankArticle.url).into(view.image)
        }, {pos -> go2Photo(mlist[pos]) })
        recyclerView.adapter = adapter

    }

    private fun  go2Photo(gankArticle: GankArticle) {
        val intent = Intent(act, PhotoActivity::class.java)
        intent.putExtra("url", gankArticle.url)
        activity!!.startActivity(intent)
    }

    override fun getType(): String {
        return Type.福利.name
    }

    override fun onResume() {
        super.onResume()
    }

    companion object {
        fun getInstance(): MeiZhiContainerFragment = MeiZhiContainerFragment()
    }

}