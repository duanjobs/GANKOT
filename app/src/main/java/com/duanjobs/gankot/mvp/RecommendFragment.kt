package com.duanjobs.gankot.mvp

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.view.View
import com.bumptech.glide.Glide
import com.duanjobs.gankot.base.BaseFragment
import com.duanjobs.gankot.R
import com.duanjobs.gankot.bean.GankArticle
import com.duanjobs.gankot.bean.Type
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_recommend.*
import okhttp3.ResponseBody
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.toast
import org.json.JSONObject
import com.duanjobs.gankot.adapter.BaseAdapter
import kotlinx.android.synthetic.main.item_recommend.view.*

/**
 * Created by duanjobs on 17/9/1.
 */
class RecommendFragment(override val layoutId: Int = R.layout.fragment_recommend) : BaseFragment()
        , IndexContract.RecommendView {
    var mPresenter: IndexContract.RecommendPresenter? = null
    var date: String? = null
    var imageUrl: String? = null

    var adapter: BaseAdapter<GankArticle>?=null

    override fun setPresenter(presenter: IndexContract.RecommendPresenter) {
        mPresenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        date = arguments.getString("date")
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter!!.getData(date!!)
    }

    override fun setData(result: ResponseBody) {
        val jsonObject = JSONObject(result.string())
        val error = jsonObject.getBoolean("error")
        if (error) {
            toast("加载失败")
        }

        val results = jsonObject.getJSONObject("results")
        val data: MutableList<GankArticle> = arrayListOf()

        val gson = Gson()
        val type = object : TypeToken<List<GankArticle>>() {}.type
        results.keys().forEach {
            if (it != Type.福利.name) {
                data.addAll(gson.fromJson<List<GankArticle>>(results.getJSONArray(it).toString(), type))
            } else {
                val array = results.getJSONArray(it)
                if (array.length() >= 0) {
                    imageUrl = array.getJSONObject(0).getString("url")
                }
            }
        }
        setUpView(data)
    }

    private fun setUpView(data: List<GankArticle>) {
        Glide.with(act)
                .load(imageUrl!!)
                .into(welFare)

        adapter = BaseAdapter(R.layout.item_recommend, data,{view, gankArticle ->
            val format = "<font color='#757575' >${gankArticle!!.who}</font>"
            view.content.text = Html.fromHtml("${gankArticle.desc}(${format})")
            view.title.text = gankArticle.type

        },{pos -> go2Detail(data[pos]) })
        recyclerView.layoutManager = LinearLayoutManager(act)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = adapter

    }

    companion object {
        fun getInstance(date: String): RecommendFragment {
            val fragment: RecommendFragment = RecommendFragment()
            val args: Bundle = Bundle()
            if (!date.isEmpty()) {
                args.putString("date", date)
            }
            fragment.arguments = args
            return fragment
        }
    }

    fun go2Detail(article: GankArticle) {
        val intent = Intent(act, GankArticleDetailActivity::class.java)
        intent.putExtra("desc", article.desc)
        intent.putExtra("url", article.url)
        startActivity(intent)
    }
}