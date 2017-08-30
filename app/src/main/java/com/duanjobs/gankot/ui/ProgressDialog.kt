package com.duanjobs.gankot.ui

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Context
import android.widget.ImageView
import com.duanjobs.gankot.R

/**
 * Created by duanjobs on 17/8/28.
 */
class LoadDialog(context: Context) : Dialog(context, R.style.CustomProgressDialog) {
    private var img: ImageView? = null

    init {
        setContentView(R.layout.loading_dialog)
        img = findViewById(R.id.img) as ImageView
    }

    override fun show() {
        super.show()
        initAnim()
    }

    override fun dismiss() {
        super.dismiss()
    }

    private fun initAnim() {
        val animator = ObjectAnimator.ofFloat(img, "rotation", 0f, 359f)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.duration = 2000
        animator.start()
    }
}