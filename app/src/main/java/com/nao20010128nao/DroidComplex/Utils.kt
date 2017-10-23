package com.nao20010128nao.DroidComplex

import android.support.v7.widget.Toolbar
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.widget.TextView

/**
 * Created by lesmi on 17/10/23.
 */

fun Toolbar.titleTextView(): TextView? {
    if (TextUtils.isEmpty(title)) return null
    try {
        val f = javaClass.getDeclaredField("mTitleTextView")
        f.isAccessible = true
        return f.get(this) as TextView
    } catch (e: NoSuchFieldException) {
    } catch (e: IllegalAccessException) {
    }

    try {
        val f = Toolbar.LayoutParams::class.java.getDeclaredField("mViewType")
        f.isAccessible = true
        for (i in 0 until childCount) {
            val v = getChildAt(i)
            if (v is TextView) {
                val lp = v.getLayoutParams()
                val viewType = f.get(lp) as Int
                if (viewType == 1) {
                    if (v.text == title) {
                        return v
                    }
                }
            }
        }
    } catch (e: NoSuchFieldException) {
    } catch (e: IllegalAccessException) {
    } catch (e: SecurityException) {
    } catch (e: IllegalArgumentException) {
    }
    return null
}

fun CharSequence.styled(vararg items: Any): CharSequence{
    val sb= SpannableStringBuilder()
    sb.append(this)
    items.forEach {
        sb.setSpan(it,0,sb.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
    return sb
}

fun CharSequence.join(cs:List<CharSequence>): CharSequence{
    val sb= SpannableStringBuilder()
    cs.forEachIndexed {index,it->
        if(index!=0){
            sb.append(this)
        }
        sb.append(it)
    }
    return sb
}
