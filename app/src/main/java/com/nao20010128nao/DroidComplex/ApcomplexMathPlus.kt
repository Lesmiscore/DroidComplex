package com.nao20010128nao.DroidComplex

import org.apfloat.Apcomplex
import org.apfloat.Apfloat
import org.apfloat.ApfloatMath

/**
 * Makes Apcomplex by giving degrees to Apfloat
 */
inline fun Apfloat.deg(deg: Apfloat): Apcomplex = rad(ApfloatMath.toRadians(deg))

/**
 * Makes Apcomplex by giving radians to Apfloat
 */
inline fun Apfloat.rad(deg: Apfloat): Apcomplex = Apcomplex(
        ApfloatMath.cos(deg).multiply(this),
        ApfloatMath.sin(deg).multiply(this))
