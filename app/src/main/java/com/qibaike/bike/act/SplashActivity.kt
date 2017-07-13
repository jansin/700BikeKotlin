package com.qibaike.bike.act
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle

import com.qibaike.bike.R
import com.qibaike.bike.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SplashActivity(override val layoutResourceId: Int = R.layout.activity_splash) : BaseActivity() {

    private val ANIM_TIME = 2000
    private val SCALE_END = 1.15f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Observable.timer(1000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe { startAnim() }
    }

    private fun startAnim() {
        val animatorX = ObjectAnimator.ofFloat(splashbg_iv, "scaleX", 1f, SCALE_END)
        val animatorY = ObjectAnimator.ofFloat(splashbg_iv, "scaleY", 1f, SCALE_END)

        val translationY = ObjectAnimator.ofFloat(titleTv, "translationY", 150f, 300f)
        val alpha = ObjectAnimator.ofFloat(titleTv, "alpha", 0f, 1f)
        val set = AnimatorSet()
        set.setDuration(ANIM_TIME.toLong()).play(translationY).with(alpha).before(animatorX).before(animatorY)
        set.start()
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                this@SplashActivity.finish()
            }
        })
    }
}
