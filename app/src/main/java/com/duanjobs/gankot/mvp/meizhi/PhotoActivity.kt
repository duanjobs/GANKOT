package com.duanjobs.gankot.mvp.meizhi

import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.duanjobs.gankot.base.BaseActivity
import com.duanjobs.gankot.R
import kotlinx.android.synthetic.main.activity_photo.*

/**
 * Created by duanjobs on 17/9/8.
 */
class PhotoActivity(override val layoutId: Int=R.layout.activity_photo) :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        val url = intent.getStringExtra("url")
        if (TextUtils.isEmpty(url)) {
            finish()
            return
        }

        Glide
                .with(this)
                .load(url)
                .into(photoView)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.title = ""
        actionBar.setDisplayHomeAsUpEnabled(true)
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
}