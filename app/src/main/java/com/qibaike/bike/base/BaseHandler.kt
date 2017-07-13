package com.qibaike.bike.base

import android.os.Handler
import android.os.Message
import java.lang.ref.WeakReference

/**
 * Created by jason on 17-7-12.
 */
abstract class BaseHandler<T>(reference: T) : Handler(){

    var valuse = reference
    var mReference = WeakReference(valuse)

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        val reference = mReference!!.get()
        if (reference != null) {
            referenceHandleMessage(reference, msg)
        }
    }

    abstract fun referenceHandleMessage(reference: T, msg: Message)

}