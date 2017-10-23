package com.nao20010128nao.DroidComplex

import org.apfloat.Apcomplex
import org.apfloat.Apfloat
import org.apfloat.ApfloatMath
import java.math.RoundingMode

const val PRECISION:Long = 100

/**
 * Makes Apcomplex by giving degrees to Apfloat
 */
inline fun Apfloat.deg(deg: Apfloat): Apcomplex = rad(ApfloatMath.toRadians(deg))

/**
 * Makes Apcomplex by giving radians to Apfloat
 */
inline fun Apfloat.rad(deg: Apfloat): Apcomplex = Apcomplex(
        ApfloatMath.cos(deg)*this,
        ApfloatMath.sin(deg)*this)

inline operator fun Apfloat.plus(a:Apfloat): Apfloat = this.add(a)
inline operator fun Apfloat.minus(a:Apfloat): Apfloat = this.subtract(a)

inline operator fun Apfloat.times(a:Apfloat): Apfloat = this.multiply(a)
inline operator fun Apfloat.div(a:Apfloat): Apfloat = this.divide(a)

inline fun Apfloat.round(precision:Long,mode: RoundingMode): Apfloat=ApfloatMath.round(this,precision,mode)

/** Zero-proof tangent calculator */
fun tan(x:Apfloat,y:Apfloat): Apfloat{
    when {
        x == Apfloat.ZERO && y == Apfloat.ZERO -> // no direction: assume 0
            return Apfloat.ZERO.precision(PRECISION)
        y == Apfloat.ZERO -> // 0
            return Apfloat.ZERO.precision(PRECISION)
        x == Apfloat.ZERO -> // 90
            return ApfloatMath.pi(PRECISION)/Apfloat(2).precision(PRECISION)
        else -> return ApfloatMath.tan(y / x)
    }
}

fun tan(p:Apcomplex):Apfloat = tan(p.real(),p.imag())
