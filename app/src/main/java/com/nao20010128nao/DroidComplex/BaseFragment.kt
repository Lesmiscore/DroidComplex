package com.nao20010128nao.DroidComplex

import android.support.annotation.*
import android.support.v4.app.*
import android.view.*

/**
 * Created by lesmi on 17/10/22.
 */
abstract class BaseFragment : Fragment() {
    fun <T : View> findViewById(@IdRes id: Int): T = view!!.findViewById(id)

    /** Get its title  */
    abstract val name: String
}
