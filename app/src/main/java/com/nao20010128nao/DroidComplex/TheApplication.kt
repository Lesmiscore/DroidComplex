package com.nao20010128nao.DroidComplex

import android.app.Application
import java.lang.ref.WeakReference

/**
 * Created by lesmi on 17/10/23.
 */
class TheApplication: Application(){
    companion object {
        var instance: WeakReference<TheApplication?> = WeakReference(null)
    }

    override fun onCreate() {
        super.onCreate()
        instance= WeakReference(this)
    }
}