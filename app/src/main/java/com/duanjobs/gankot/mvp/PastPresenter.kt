package com.duanjobs.gankot.mvp

import com.duanjobs.gankot.bean.History
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

/**
 * Created by duanjobs on 17/9/5.
 */
class PastPresenter:IndexContract.PastPresenter {

    var mView: IndexContract.PastView? = null
    var mModel: IndexContract.PastModel? = null

    constructor(view: IndexContract.PastView) {
        mView = checkNotNull(view)
        mView!!.setPresenter(this)
        mModel = PastModel.getInstance()
    }

    override fun getData() {
        mModel!!.getData().
                observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe{result->Parse(result.string())}
    }

    private fun Parse(html: String) {
        val doc: Document = Jsoup.parse(html)
        val typo: Elements = doc.getElementsByClass("typo")
        var data: MutableList<History> = arrayListOf()

        var history: History

        typo.select("a").listIterator().forEach {
            history = History(it.attr("href").substring(1), it.text())
            data.add(history)
        }
        mView!!.setData(data)
    }

}