package com.nao20010128nao.DroidComplex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.*
import android.widget.*

import com.astuetz.*

import org.apfloat.*

class MainActivity : AppCompatActivity() {
    var tabs: PagerSlidingTabStrip? = null
    var pager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        tabs=findViewById(R.id.tabs)
        pager=findViewById(R.id.pager)
        tabs!!.setViewPager(pager!!)

        val adapter=UsefulPagerAdapter(supportFragmentManager)
        adapter.addTab(RealImagFragment())
        adapter.addTab(AbsDegFragment())
        adapter.addTab(AbsRadFragment())
        pager!!.adapter=adapter
    }

    class RealImagFragment : ComplexInputFragment() {
        var real: EditText?=null
        var imag: EditText?=null

        override val partA: Apfloat
            get() = Apfloat(real!!.text!!.toString())

        override val partB: Apfloat
            get() = Apfloat(imag!!.text!!.toString())

        override val complex: Apcomplex
            get() = Apcomplex(partA,partB)

        override val name: String
            get() = context!!.resources!!.getString(R.string.real_imag)

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val v = inflater!!.inflate(R.layout.complex_real_imag, container, false)
            real = v.findViewById(R.id.real)
            imag = v.findViewById(R.id.imag)
            return v
        }
    }

    class AbsDegFragment : ComplexInputFragment() {
        var abs: EditText?=null
        var deg: EditText?=null

        override val partA: Apfloat
            get() = Apfloat(abs!!.text!!.toString())

        override val partB: Apfloat
            get() = Apfloat(deg!!.text!!.toString())

        override val complex: Apcomplex
            get() = partA.deg(partB)

        override val name: String
            get() = context!!.resources!!.getString(R.string.abs_deg)

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val v = inflater!!.inflate(R.layout.complex_abs_deg, container, false)
            abs = v.findViewById(R.id.abs)
            deg = v.findViewById(R.id.deg)
            return v
        }
    }

    class AbsRadFragment : ComplexInputFragment() {
        var abs: EditText?=null
        var rad: EditText?=null

        override val partA: Apfloat
            get() = Apfloat(abs!!.text!!.toString())

        override val partB: Apfloat
            get() = Apfloat(rad!!.text!!.toString())

        override val complex: Apcomplex
            get() = partA.rad(partB)

        override val name: String
            get() = context!!.resources!!.getString(R.string.abs_rad)

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val v = inflater!!.inflate(R.layout.complex_abs_rad, container, false)
            abs = v.findViewById(R.id.abs)
            rad = v.findViewById(R.id.rad)
            return v
        }
    }

    abstract class ComplexInputFragment : BaseFragment() {

        /** Get real and abs part  */
        abstract val partA: Apfloat
        /** Get imag, deg and rad part  */
        abstract val partB: Apfloat
        /** Get final complex number  */
        abstract val complex: Apcomplex

        abstract override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
    }
}
