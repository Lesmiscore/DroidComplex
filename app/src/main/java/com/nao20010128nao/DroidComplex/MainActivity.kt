package com.nao20010128nao.DroidComplex

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.*
import android.widget.*

import com.astuetz.*
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

import org.apfloat.*
import java.io.PrintWriter
import java.io.StringWriter
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    companion object{
        val DISPLAY_PRECISION:Long = 10
    }

    var tabs: PagerSlidingTabStrip? = null
    var pager: ViewPager? = null
    var adView: AdView? = null
    var calc: Button? = null
    var result: TextView? = null

    var currentValue: (() -> Apcomplex)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        tabs = findViewById(R.id.tabs)
        pager = findViewById(R.id.pager)
        adView = findViewById(R.id.adView)
        calc = findViewById(R.id.calculate)
        result = findViewById(R.id.complex_data)

        val adapter = UsefulPagerAdapter(supportFragmentManager)
        adapter.addTab(RealImagFragment())
        adapter.addTab(AbsDegFragment())
        adapter.addTab(AbsRadFragment())
        pager!!.adapter = adapter
        tabs!!.setViewPager(pager!!)
        currentValue = fun(): Apcomplex = (adapter.getItem(pager!!.currentItem) as ComplexInputFragment).complex

        adView!!.loadAd(AdRequest.Builder().build())

        calc!!.setOnClickListener {
            try {
                calculate()
            } catch (e: Throwable) {
                result!!.text = "\n".join(
                        resources.getString(R.string.error),
                        StringWriter().apply { e.printStackTrace(PrintWriter(this)) }.toString()
                ).styled(ForegroundColorSpan(Color.RED), StyleSpan(Typeface.BOLD))
            }
        }

        runOnUiThread {
            val titleView = findViewById<Toolbar>(R.id.toolbar)!!.titleTextView()
            if (titleView != null) {
                titleView.gravity = Gravity.CENTER
            }
        }
    }

    private fun calculate() {
        val complex = currentValue!!()
        val tangent= tan(complex)
        val components: MutableList<CharSequence> = ArrayList()
        components += (resources.getString(R.string.real_part) + ": ").styled(StyleSpan(Typeface.BOLD_ITALIC), ForegroundColorSpan(Color.WHITE))
        components += complex.real().round(DISPLAY_PRECISION, RoundingMode.HALF_UP).toString(true)
        components += "\n"
        components += (resources.getString(R.string.imag_part) + ": ").styled(StyleSpan(Typeface.BOLD_ITALIC), ForegroundColorSpan(Color.WHITE))
        components += complex.imag().round(DISPLAY_PRECISION, RoundingMode.HALF_UP).toString(true)
        components += "\n"
        components += (resources.getString(R.string.abs_value) + ": ").styled(StyleSpan(Typeface.BOLD_ITALIC), ForegroundColorSpan(Color.WHITE))
        components += ApcomplexMath.abs(complex).round(DISPLAY_PRECISION, RoundingMode.HALF_UP).toString(true)
        components += "\n"
        components += (resources.getString(R.string.angle_in_deg) + ": ").styled(StyleSpan(Typeface.BOLD_ITALIC), ForegroundColorSpan(Color.WHITE))
        components += toDeg(tangent).round(DISPLAY_PRECISION, RoundingMode.HALF_UP).toString(true)
        components += "\n"
        components += (resources.getString(R.string.angle_in_rad) + ": ").styled(StyleSpan(Typeface.BOLD_ITALIC), ForegroundColorSpan(Color.WHITE))
        components += tangent.round(DISPLAY_PRECISION, RoundingMode.HALF_UP).toString(true)
        components += "\n"
        components += (resources.getString(R.string.angle_in_rad_div_pi) + ": ").styled(StyleSpan(Typeface.BOLD_ITALIC), ForegroundColorSpan(Color.WHITE))
        components += (tangent/ApfloatMath.pi(PRECISION)).round(DISPLAY_PRECISION, RoundingMode.HALF_UP).toString(true)
        components += "\n"
        result!!.text = "".join(components)
    }

    class RealImagFragment : ComplexInputFragment() {
        var real: EditText? = null
        var imag: EditText? = null

        override val partA: Apfloat
            get() = Apfloat(real!!.text!!.toString()).precision(PRECISION)

        override val partB: Apfloat
            get() = Apfloat(imag!!.text!!.toString()).precision(PRECISION)

        override val complex: Apcomplex
            get() = Apcomplex(partA, partB)

        override val name: String
            get() = nonNullContext.resources.getString(R.string.real_imag)

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val v = inflater!!.inflate(R.layout.complex_real_imag, container, false)
            real = v.findViewById(R.id.real)
            imag = v.findViewById(R.id.imag)
            return v
        }
    }

    class AbsDegFragment : ComplexInputFragment() {
        var abs: EditText? = null
        var deg: EditText? = null

        override val partA: Apfloat
            get() = Apfloat(abs!!.text!!.toString()).precision(PRECISION)

        override val partB: Apfloat
            get() = Apfloat(deg!!.text!!.toString()).precision(PRECISION)

        override val complex: Apcomplex
            get() = partA.deg(partB)

        override val name: String
            get() = nonNullContext.resources.getString(R.string.abs_deg)

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val v = inflater!!.inflate(R.layout.complex_abs_deg, container, false)
            abs = v.findViewById(R.id.abs)
            deg = v.findViewById(R.id.deg)
            return v
        }
    }

    class AbsRadFragment : ComplexInputFragment() {
        var abs: EditText? = null
        var rad: EditText? = null

        override val partA: Apfloat
            get() = Apfloat(abs!!.text!!.toString()).precision(PRECISION)

        override val partB: Apfloat
            get() = Apfloat(rad!!.text!!.toString()).precision(PRECISION)

        override val complex: Apcomplex
            get() = partA.rad(partB)

        override val name: String
            get() = nonNullContext.resources.getString(R.string.abs_rad)

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
