package com.example.harajtask.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.res.Resources
import android.os.Handler
import android.view.View
import androidx.core.animation.addListener
import androidx.core.animation.doOnEnd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

fun dpToPx(dp: Float): Float {
    return (dp * Resources.getSystem().displayMetrics.density)
}

fun Any.delay(
    duration: Long, handler: () -> Unit
) {
    Handler().postDelayed({
        handler.invoke()
    }, duration)
}

fun View.animToY(
    y: Float,
    animate: Boolean? = true,
    after: Long? = 0,
    duration: Long? = 500,
    onEnd: (() -> Unit)? = {},
) {
    AnimatorSet().apply {
        play(ObjectAnimator.ofFloat(this@animToY, View.TRANSLATION_Y, dpToPx(y)).apply {
            this.duration = if (animate != false) duration ?: 500 else 0
            doOnEnd {
                onEnd?.invoke()
            }
            interpolator?.let {
                this.interpolator = it
            }
        }).after(after ?: 0)
        start()
    }
}

fun View.fadeShow(onEnd: (() -> Unit)? = {}, duration: Long? = 1000, after: Long? = 0) {
    this.alpha = 0f
    this.visibility = View.VISIBLE
    AnimatorSet().apply {
        play(ObjectAnimator.ofFloat(this@fadeShow, View.ALPHA, 1f).apply {
            this.duration = duration ?: 1000
            addListener(onEnd = {
                onEnd?.invoke()
            })
        }).after(after ?: 0)
        start()
    }
}

fun Any.io(runnable:suspend ()->Unit){
    CoroutineScope(IO).launch {
        runnable.invoke()
    }
}

fun View.hide(){
    this.visibility = View.GONE
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun String.isArabic():Boolean{
    return Character.getDirectionality(this[0]) == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC
}

fun Any.main(runnable:suspend ()->Unit){
    CoroutineScope(Main).launch {
        runnable.invoke()
    }
}
