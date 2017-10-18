package com.duanjobs.gankot.mvp.past

import android.os.Bundle
import android.view.MenuItem
import com.duanjobs.gankot.base.BaseActivity
import com.duanjobs.gankot.R
import com.duanjobs.gankot.mvp.recommend.RecommendFragment
import com.duanjobs.gankot.mvp.recommend.RecommendPresenter
import kotlinx.android.synthetic.main.activity_gank_article_detail.*

/**
 * Created by duanjobs on 17/9/8.
 */
class PastDetailActivity(override val layoutId: Int = R.layout.activity_past_detail)
    : BaseActivity() {
    var date: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        initView()
    }

    private fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = date
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        var recommend = RecommendFragment.getInstance(date!!)
        RecommendPresenter(recommend)
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, recommend)
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun getData() {
        date = intent.getStringExtra("date")
    }
}