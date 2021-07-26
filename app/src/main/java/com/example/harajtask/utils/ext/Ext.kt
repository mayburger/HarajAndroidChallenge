package com.example.harajtask.utils.ext

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

fun Any.io(runnable:suspend ()->Unit){
    CoroutineScope(IO).launch {
        runnable.invoke()
    }
}


fun String.isArabic():Boolean{
    return Character.getDirectionality(this[0]) == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC
}

fun Any.main(runnable:suspend ()->Unit){
    CoroutineScope(Main).launch {
        runnable.invoke()
    }
}
