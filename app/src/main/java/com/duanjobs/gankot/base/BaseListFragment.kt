package com.duanjobs.gankot.base

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.View
import com.bumptech.glide.Glide
import com.duanjobs.gankot.R
import com.duanjobs.gankot.adapter.BaseAdapter
import com.duanjobs.gankot.bean.GankArticle
import com.duanjobs.gankot.mvp.GankArticleDetailActivity
import com.duanjobs.gankot.mvp.IndexContract
import com.duanjobs.gankot.mvp.ListPresenter
import kotlinx.android.synthetic.main.item_girl.view.*
import kotlinx.android.synthetic.main.item_list_article.view.*
import kotlinx.android.synthetic.main.list_fragment_base_layout.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.act
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by duanjobs on 17/8/29.
 */
abstract class BaseListFragment(override val layoutId: Int = R.layout.list_fragment_base_layout) : BaseFragment()
        , IndexContract.ListView {

    val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    var pageNumber = 1
    var isFresh = true

    var adapter: BaseAdapter<GankArticle>? = null
    var mPresenter: ListPresenter? = null
    var mlist = ArrayList<GankArticle>()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //初始化列表 并设置上拉下拉刷新
        initRecyleView()
        swipeLayout.setOnRefreshListener {
            pageNumber = 1
            isFresh = true
            loadData(pageNumber, getType())
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView?.canScrollVertically(1)!!) {
                    isFresh = false
                    loadData(++pageNumber, getType())
                }
            }
        })
        loadData(pageNumber, getType())
    }

    fun loadData(pageNumber: Int, type: String) {
        mPresenter!!.getData(pageNumber, type)
    }

    open fun initRecyleView() {
        recyclerView.layoutManager = LinearLayoutManager(act)
        adapter = BaseAdapter(R.layout.item_list_article,mlist,{view, gankArticle ->
            view.title.text = gankArticle!!.desc
            view.who.text = gankArticle!!.who
            view.type.text = gankArticle!!.type
            view.publishedAt.text = DateUtils.getRelativeTimeSpanString(sdf.parse(gankArticle!!.publishedAt).time)
            if (gankArticle!!.images == null || gankArticle!!.images.size == 0) {
                view.article_image.visibility = View.GONE
            } else {
                view.article_image.visibility = View.VISIBLE
                val width: Int = context.resources.getDimension(R.dimen.article_image_width).toInt()
                Glide.with(context).load("${gankArticle!!.images[0]}?imageView2/0/w/$width").into(view.article_image)
            }},{ pos ->  go2Detail(mlist[pos])
        })
        recyclerView.adapter = adapter
    }

    override fun setData(results: List<GankArticle>) {
        swipeLayout.isRefreshing = false
        if (isFresh) {
            mlist.clear()
            mlist.addAll(results)
            adapter!!.notifyDataSetChanged()

        } else {
            mlist.addAll(results)
            adapter!!.notifyItemRangeInserted(mlist.size - results.size, results.size)
        }
    }

    abstract fun getType(): String


    override fun setPresenter(presenter: IndexContract.ListPresenter) {
        mPresenter = checkNotNull(presenter as ListPresenter)
    }

    fun go2Detail(article: GankArticle) {
        val intent = Intent(act, GankArticleDetailActivity::class.java)
        intent.putExtra("desc", article.desc)
        intent.putExtra("url", article.url)
        startActivity(intent)
    }
}