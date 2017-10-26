package com.nao20010128nao.DroidComplex

import android.os.AsyncTask

/**
 * Created by lesmi on 17/10/26.
 */
class KotlinAsyncTask<Params, Result>: AsyncTask<Params, Nothing, Result>() {
    var work: ((params: Array<out Params>)->Result?) ={ null }
    var result: ((result: Result)->Unit) ={}

    override fun doInBackground(vararg params: Params): Result? = work(params)

    override fun onPostExecute(result: Result) = result(result)

    operator inline fun invoke(vararg params: Params): AsyncTask<Params, Nothing, Result> = execute(*params)
}