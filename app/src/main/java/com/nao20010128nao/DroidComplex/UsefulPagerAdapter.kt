package com.nao20010128nao.DroidComplex

import android.support.v4.app.*

import java.util.*

class UsefulPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    internal var pages: MutableList<BaseFragment> = ArrayList()

    fun addTab(page: BaseFragment) {
        pages.add(page)
        notifyDataSetChanged()
    }

    override fun getCount(): Int = pages.size

    override fun getItem(p1: Int): Fragment = pages[p1]

    override fun getPageTitle(position: Int): CharSequence = pages[position].name
}
